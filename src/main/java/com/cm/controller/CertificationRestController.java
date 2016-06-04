package com.cm.controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cm.entity.Certification;
import com.cm.entity.Question;
import com.cm.exception.CertificationNotFoundException;
import com.cm.service.CandidateService;
import com.cm.service.CertificationService;
import com.cm.service.QuestionService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path="candidate/{id}/take/{id_certif}")
public class CertificationRestController {
	
	@Autowired
	CertificationService certificationService;
	@Autowired
	CandidateService candidateService;
	@Autowired
	QuestionService questionService;
	
	private static final Logger LOGGER = Logger.getLogger(CertificationRestController.class.getName());

	
	
	
	
	
	@RequestMapping(path="/listQuest", 
			method=RequestMethod.GET,
			produces="application/json")
	public @ResponseBody String wantListQuest(@PathVariable String id_certif) throws JsonProcessingException{
		Certification certification = certificationService.findCertification(id_certif);
		if(certification == null){
			throw new CertificationNotFoundException();
		}
		List<Question> randomQuestions = new ArrayList<>();
	    List<Question> copy = questionService.findAllQuestionForACertification(certification.getId());
	    
	    SecureRandom rand = new SecureRandom();
	    for (int i = 0; i < certification.getNb_question(); i++) {
	        randomQuestions.add(copy.remove(rand.nextInt(copy.size())));
	    }
	    Certification certif = new Certification();
	    certif.setQuestions(randomQuestions);
	    certif.setDuration(certification.getDuration());
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.setSerializationInclusion(Include.NON_NULL);
	    String jsonInString = mapper.writeValueAsString(certif);
	    LOGGER.info(jsonInString);
	    return jsonInString;
	}

}
