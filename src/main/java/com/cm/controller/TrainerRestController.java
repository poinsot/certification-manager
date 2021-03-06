package com.cm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cm.entity.Certification;
import com.cm.entity.Question;
import com.cm.entity.Response;
import com.cm.entity.Trainer;
import com.cm.exception.TrainerNotFoundException;
import com.cm.service.CertificationService;
import com.cm.service.QuestionService;
import com.cm.service.ResponseService;
import com.cm.service.TrainerService;

@RestController
@RequestMapping(path="/trainer")
public class TrainerRestController {
	
	@Autowired
	TrainerService trainerService;
	
	@Autowired
	CertificationService certificationService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	ResponseService responseService;
	
	
	private static final Logger LOGGER = Logger.getLogger(TrainerRestController.class.getName());

	
	

	@RequestMapping(value = "/{id}/createcertif" ,
			method = RequestMethod.POST,
			produces="application/json")
	public @ResponseBody String createCertif(@RequestBody Certification certif, @PathVariable String id, HttpServletResponse resp) throws IOException {
		Trainer trainer = trainerService.findById(id);
		if(trainer == null)
			throw new TrainerNotFoundException();
		Map<String, String> errors = validateCertificationMeta(certif);
		if(errors.isEmpty()){
			certif.setId_trainer(trainer.getId());
			certif = certificationService.createCertification(certif);
		}
		errors.putAll(validateCertificationQuestion(certif));
		LOGGER.info(JSONObject.wrap(errors).toString());
		if(!errors.isEmpty()){
			return JSONObject.wrap(errors).toString();
		}
		LOGGER.info(certif.toString());
		
		for (Question question : certif.getQuestions()) {
			question.setId_certif(certif.getId());
			LOGGER.info(question.toString());
			question = questionService.createQuestion(question);
			for (Response response : question.getResponses()) {
				response.setId_question(question.getId());
				LOGGER.info(response.toString());
				responseService.createResponse(response);
			}
		}
		resp.sendRedirect("/trainer/"+trainer.getId()+"/home");
		return "";
	}

	private Map<String, String> validateCertificationQuestion(Certification certif) {
		Map<String, String> errors = new HashMap<String, String>();
		for (Question question : certif.getQuestions()) {
			if(!validateText(question.getText())){
				errors.putIfAbsent("question Text", question.getText());		
			}
			for (Response response : question.getResponses()) {
				if(!validateText(response.getText())){
					errors.putIfAbsent("response Text", response.getText());		
				}
			}
		}
		
		return errors;
	}

	private boolean validateText(String text) {
		return text != null;
	}

	public Map<String,String> validateCertificationMeta(Certification certif) {
		Map<String, String> errors = new HashMap<String, String>();
		if(!validateTitle(certif.getTitle())){
			errors.putIfAbsent("title", certif.getTitle());		
		}
		if(!validatePercentSuccess(certif.getPercent_success())){
			errors.putIfAbsent("percentSuccess", String.valueOf(certif.getPercent_success()));
		}
		if(!validateNbQuest(certif.getNb_question())){
			errors.putIfAbsent("nb_quest", String.valueOf(certif.getNb_question()));			
		}
		if(!validateDuration(certif.getDuration())){
			errors.putIfAbsent("duration", String.valueOf(certif.getDuration()));			
		}
		return errors;
		
	}

	private boolean validateDuration(Integer duration) {
		return duration != null && duration > 0;
	}

	private boolean validateNbQuest(Integer nb_question) {
		return nb_question != null && nb_question > 0;
	}

	private boolean validatePercentSuccess(Integer percent_success) {
		return percent_success != null && percent_success >= 0 && percent_success <= 100;
	}

	private boolean validateTitle(String title) {
		return title != null && title.length() >= 3;
	}


}
