package com.cm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cm.entity.Candidate;
import com.cm.exception.CandidateAlreadyExistException;
import com.cm.exception.CandidateNotFoundException;
import com.cm.service.CandidateService;
import com.cm.service.CertificationService;
import com.cm.service.MailSenderService;
import com.cm.validator.Validator;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

	private static final Logger LOGGER = Logger.getLogger(CandidateController.class.getName());
	@Autowired
	CandidateService candidateService;
	@Autowired
	MailSenderService mailSender;
	@Autowired
	CertificationService certificationService;
	
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
				mailSender.sendConfirmationCode(mail, candidate.getValidation_code(), "Candidate");
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

	@RequestMapping(path="/confirm/{confirmation_code}", 
			method={RequestMethod.GET})
	public String confirmationInscriptionDone(@PathVariable String confirmation_code){
		Candidate candidate = candidateService.findByValidationCode(confirmation_code);
		if(candidate == null)
			return "redirect:/error/confirmationcodeinvalide";
		candidateService.updateInscriptionValidateOfACandidate(candidate.getValidation_code());
		candidate = candidateService.findByValidationCode(confirmation_code);
		return "confirmOK";
	}

	@RequestMapping(path="/login",method={RequestMethod.GET})
	public String login() {
		return "candidateLoggin";
	}
	
	@RequestMapping(path="/login",
			method=RequestMethod.POST)
	public String loginVerif(@ModelAttribute("candidate") Candidate candidate, Model model, HttpServletResponse resp) {
		Map<String,String> errors = new HashMap<>();
		
		LOGGER.info(candidate.getMail());
		Candidate candidateSearch = candidateService.findByEmail(candidate.getMail());
		if(candidateSearch == null){
			throw new CandidateNotFoundException();
		}
		if (!BCrypt.checkpw(candidate.getPwd(), candidateSearch.getPwd())){
			errors.putIfAbsent("pwd", candidate.getPwd());
		}
		if(!errors.isEmpty()){
			return JSONObject.wrap(errors).toString();
		}
		//TODO make session instead of cookie
		Cookie cookie = new Cookie("CandidateLogged", candidate.getMail());
		cookie.setMaxAge(3600);
		resp.addCookie(cookie);
		model.addAttribute("candidate", candidate);	
		return String.format("redirect:/candidate/%s/home",candidateSearch.getId());
	}
	
	@RequestMapping(path="/{id}/home",
			method=RequestMethod.GET)
	public String candidateHome(@PathVariable String id, Model model, HttpServletResponse resp,  HttpServletRequest req) {
		Candidate candidate = candidateService.findById(id);
		if(candidate == null){
			throw new CandidateNotFoundException();
		}
		switch(isCandidateLogged(id, req, candidate)){
		case "notValidate" : return "redirect:/";
		case "notLogged" : return "redirect:/candidate/login";
		}
		candidate.setListCertif(certificationService.findAllCertifications());
		model.addAttribute("candidate", candidate);
		return "candidateHome";
	}
	
	private String isCandidateLogged(String id, HttpServletRequest req, Candidate candidate) {
		if(candidate.getInscription_validate() == 0)
			return "notValidate";
		Cookie[] listCookie = req.getCookies();
		boolean cookieIsPres = false;
		for (Cookie cookie : listCookie) {
			if(cookie.getName().equals("CandidateLogged")){
				cookieIsPres = true;
				if(!cookie.getValue().equals(candidate.getMail())){
					return "notLogged";
				}
			}
		}
		if(!cookieIsPres){
			return "notLogged";
		}
		return "";
	}
	
}
