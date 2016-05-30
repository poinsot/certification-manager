package com.cm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cm.entity.Candidate;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cm.entity.Trainer;
import com.cm.exception.TrainerAlreadyExistException;
import com.cm.service.MailSenderService;
import com.cm.service.TrainerService;
import com.cm.utils.DateUtils;
import com.cm.validator.Validator;

@Controller
@RequestMapping(path="/trainer")
public class TrainerController {
	
	private static final Logger LOGGER = Logger.getLogger(TrainerController.class.getName());
	
	@Autowired
	TrainerService trainerService;
	
	@Autowired
	MailSenderService mailSender;
	
	
	/**
	 * Mapping of trainerRegister web page
	 * @param model
	 * @return trainerRegister.jsp
	 */
	
	@RequestMapping(path="/register",method={RequestMethod.GET})
	public String trainerRegisterForm(Model model) {
		return "trainerRegister";
	}
	
	/**
	 * POST process on register form
	 * @param trainer : the trainer entity linked to DB
	 * @param model : the model used in MVC design pattern
	 * @param req : the HTTP request (used here to get the date before formating it
	 * @return :  redirection to confirmation of the inscription or of the exception generated
	 * 
	 * This function treat the form 'trainerRegister', validate the fields.
	 * If fields are correctly formated, a mail is send with a confirmation URL used to activate the account.
	 * If fields are badly formated, it redirects to empty registration form again
	 */
	@RequestMapping(path="/register",method={RequestMethod.POST})
	public String processTrainerRegistrationForm(@ModelAttribute("trainer") Trainer trainer, Model model, HttpServletRequest req) {
		
		String date = req.getParameter("birthday");
		Map<String, String> errors = new HashMap<String, String>();
		if(validateFields(trainer,errors,model,date)) {
			try{
				trainerService.registerTrainer(trainer);
				mailSender.sendConfirmationCode(trainer.getMail(), trainer.getValidation_code(), "Trainer");
			}catch(IllegalArgumentException e){
				LOGGER.log(Level.SEVERE, e.toString(), e );
			}catch(TrainerAlreadyExistException e){
				LOGGER.log(Level.SEVERE, e.toString(), e );
				return "redirect:/error/tr_alreadyexist";
			}
			catch(Exception e){
				LOGGER.log(Level.SEVERE, e.toString(), e );
				return "redirect:/error/database";
			}

			return "redirect:confirmation";
			
		}

		model.addAttribute("trainer", trainer);	
		LOGGER.info(errors.toString());
		return "trainerRegister";
	}
	
	/**
	 * Mapping of registration confirmation page
	 * @return candidateRegisterConfirmation.jsp
	 */
	@RequestMapping(path="/confirmation", 
			method={RequestMethod.GET})
	public String confirmationInscription(){
		return "trainerRegisterConfirmation";
	}
	
	/**
	 * 
	 * @param confirmation_code : the confirmation included in the URL (the one sent to the user by mail)
	 * @return confirmOK.jsp or Exception Management redirection
	 * This function process confirmation URL for trainers.
	 * If confirmation code exist the trainer is set validated and user is redirected to confirmOK page
	 */
	@RequestMapping(path="/confirm/{confirmation_code}", 
			method={RequestMethod.GET})
	public String confirmationInscriptionDone(@PathVariable String confirmation_code){
		Trainer trainer = trainerService.findByValidationCode(confirmation_code);
		if(trainer == null)
			return "redirect:/error/confirmationcodeinvalide";
		trainerService.updateInscriptionValidateOfACandidate(trainer);
		return "confirmOK";
	}
	
	/**
	 * 
	 * @param trainer = The Trainer's entity with values entered in the form
	 * @param errors = a HashMap to get track of errors
	 * @param model = the model
	 * @param date = the formated date
	 * @return boolean (true if fields are correctly mapped, false else) 
	 */
	
	private boolean validateFields(Trainer trainer, Map<String,String> errors,Model model, String date) {
		
		String lastname = trainer.getLastname();
		String firstname= trainer.getFirstname();
		String mail= trainer.getMail();
		String pwd = trainer.getPwd();
				
		if(! Validator.validateFirstName(firstname)) {
			
			errors.putIfAbsent("firstName", "firstName invalid => + " + firstname);			
		}
		
		if(! Validator.validateLastName(lastname)) {
			errors.putIfAbsent("lastName", "lastName invalid => " + lastname);
		}
		
		if(! Validator.validatePwd(pwd)) {
			errors.putIfAbsent("pwd", "pwd invalid => " + pwd);		
		}
		else {
			String pw_hash = BCrypt.hashpw(pwd, BCrypt.gensalt()); 
			trainer.setPwd(pw_hash);	
		}
		
		if(! Validator.validateBirthDate(date)) {
			errors.putIfAbsent("birthDate", "birthDate invalid => " + date);
		}
		else{
			trainer.setBirthdate(DateUtils.dateParser(date,"dd/MM/yyyy"));
		}
					
		if(! Validator.validateMail(mail)) {
			errors.putIfAbsent("mail", "mail invalid => " + mail);
		}
		
		/*Trainer checkTrainer = trainerService.findTrainer("glenn.judeau@gmail.com");
		if (BCrypt.checkpw( "TooCool24",checkTrainer.getPwd()))
		    LOGGER.info("IT MATCHES !!!!!!!!!!!!!!!!!!");
		else
			LOGGER.info("IT  DOESN'T MATCHES !!!!!!!!!!!!!!!!!!");*/
				
		return(errors.isEmpty());
	}
}
