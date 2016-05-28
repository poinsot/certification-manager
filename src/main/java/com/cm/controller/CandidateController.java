package com.cm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cm.entity.Candidate;
import com.cm.exception.CandidateAlreadyExistException;
import com.cm.service.CandidateService;
import com.cm.service.FakeMailSenderService;
import com.cm.validator.Validator;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

	private static final Logger LOGGER = Logger.getLogger(CandidateController.class.getName());
	@Autowired
	CandidateService candidateService;
	@Autowired
	FakeMailSenderService fakeMailSender;

	/**
	 * mapping to the jsp candidateRegister
	 * @return candidateRegister.jsp
	 */
	@RequestMapping(path="/register", 
			method={RequestMethod.GET})
	public String registerFormCandidate(){
		return "candidateRegister";
	}


	/**
	 * get all value from the form to register a candidate
	 * @param candidate : Candidate : the entity to register
	 * @param model : Model : the model
	 * @param req : HttpServletRequest : to get all parameters needed
	 * @return redirection to confirmation of the inscription or to a error page if the inscription went bad
	 */
	@RequestMapping(path="/register", 
			method=RequestMethod.POST)
	public String registerCandidate(@ModelAttribute("candidate") Candidate candidate, Model model,  HttpServletRequest req){
		Map<String, String> errors = new HashMap<String, String>();
		String lastname = candidate.getLastname();
		String firstname= candidate.getFirstname();
		String id_card_number = candidate.getId_card_number();
		String mail= candidate.getMail();
		String pwd = candidate.getPwd();
		String birthDate = req.getParameter("birthday");

		if(! Validator.validateFirstName(firstname)) {

			errors.putIfAbsent("firstName", "firstName invalid => + " + firstname);			
		}

		if(! Validator.validateLastName(lastname)) {
			errors.putIfAbsent("lastName", "lastName invalid => " + lastname);
		}

		if(! Validator.validatePwd(pwd)) {
			errors.putIfAbsent("pwd", "pwd invalid => " + pwd);			
		}
		else{
			String pw_hash = BCrypt.hashpw(pwd, BCrypt.gensalt()); 
			candidate.setPwd(pw_hash);

		}

		if(! Validator.validateIdCardNumber(id_card_number)) {
			errors.putIfAbsent("id_card_number", "id_card_number invalid  => " + id_card_number);
		}
		if(! Validator.validateBirthDate(birthDate)) {
			errors.putIfAbsent("birthDate", "birthDate invalid => " + birthDate);
		}else{
			candidate.setBirthdate(parseDate(birthDate));
		}

		if(! Validator.validateMail(mail)) {
			errors.putIfAbsent("mail", "mail invalid => " + mail);
		}

		if(errors.isEmpty()){
			try{
				candidateService.registerCandidate(candidate);
				//fakeMailSender.sendConfirmationCodeCandidate(mail, candidate.getValidation_code());
			}catch(IllegalArgumentException e){
				LOGGER.log(Level.SEVERE, e.toString(), e );
			}catch(CandidateAlreadyExistException e){
				LOGGER.log(Level.SEVERE, e.toString(), e );
				return "redirect:/error/alreadyexist";
			}
			catch(Exception e){
				LOGGER.log(Level.SEVERE, e.toString(), e );
				return "redirect:/error/database";
			}

			return "redirect:confirmation";
		}
		model.addAttribute("candidate", candidate);

		return "candidateRegister";
	}

	/**
	 * parse a String to a Date with the pattern dd/MM/yyyy
	 * @param birthDate, the String to parse
	 * @return Date
	 */
	private Date parseDate(String birthDate) {
		if(birthDate == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		try {
			return sdf.parse(birthDate);

		} catch (ParseException e) {
			return null;
		}
	}


	@RequestMapping(path="/confirmation", 
			method={RequestMethod.GET})
	public String confirmationInscription(){
		return "candidateRegisterConfirmation";
	}

}
