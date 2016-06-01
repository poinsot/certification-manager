package com.cm.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

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
import com.cm.entity.Trainer;
import com.cm.service.TrainerService;

@RestController
@RequestMapping(path="/trainer")
public class TrainerRestController {
	
	@Autowired
	TrainerService trainerService;
	
	private static final Logger LOGGER = Logger.getLogger(TrainerRestController.class.getName());

	
	

	@RequestMapping(value = "/{id}/createcertif" ,
			method = RequestMethod.POST,
			produces="application/json")
	public @ResponseBody String createCertif(@RequestBody Certification certif, @PathVariable String id) {
		Trainer trainer = trainerService.findById(id);
		if(trainer == null)
			//TODO redirect 404 error
			return "redirect:/";
		Map<String, String> errors = validateCertificationMeta(certif);
		//errors.putAll(validateCertificationQuestion(certif));
		LOGGER.info(JSONObject.wrap(errors).toString());
		return JSONObject.wrap(errors).toString();
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
