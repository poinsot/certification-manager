package com.cm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cm.CertificationManagerApplication;
import com.cm.MockObject.MockCertif;
import com.cm.MockObject.MockQuestion;
import com.cm.MockObject.MockResponse;
import com.cm.MockObject.MockTrainer;
import com.cm.entity.Certification;
import com.cm.entity.Question;
import com.cm.entity.Response;
import com.cm.entity.Trainer;

import com.cm.exception.QuestionNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=CertificationManagerApplication.class)
@Transactional 
public class ResponseServiceTest {
	
	@Autowired
	ResponseService responseService;

	@Autowired
	QuestionService questionService;
	
	@Autowired
	CertificationService certificationService;
	
	@Autowired
	TrainerService trainerService;
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateResponseWithResponseNull() {
		responseService.createResponse(null);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void testCreateResponseWithIdQuestionfNull() {
		Response response = MockResponse.getResponseMock();
		response.setId_question(null);
		responseService.createResponse(response);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateResponseWithIsCorrectNull() {
		Response response = MockResponse.getResponseMock();
		response.setIs_correct(null);
		responseService.createResponse(response);
		
	}
	
	
	
	@Test(expected=QuestionNotFoundException.class)
	public void testCreateResponseWithQuestionNotFound(){
		Response response = MockResponse.getResponseMock();
		response.setId_question(12);
		responseService.createResponse(response);
		
	}
	
	@Test
	public void testCreateResponse(){
		Trainer trainer = MockTrainer.getTrainerMock();
		trainerService.registerTrainer(trainer);
		Trainer trainerSearch = trainerService.findTrainerByMail(trainer.getMail());
		
		Certification certification = MockCertif.getCertif();
		certification.setId_trainer(trainerSearch.getId());
		certificationService.createCertification(certification);
		
		Question question = MockQuestion.getQuestionMock();
		question.setId_certif(certification.getId());
		questionService.createQuestion(question);
		
		Response response = MockResponse.getResponseMock();
		response.setId_question(question.getId());
		assertEquals("id not yet initilized", null, response.getId());
		response = responseService.createResponse(response);
		assertNotEquals("id initilized", null, response.getId());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindOneWithIdNull(){
		responseService.findOne(null);
	}
	
	@Test
	public void testFindOne(){
		Trainer trainer = MockTrainer.getTrainerMock();
		trainerService.registerTrainer(trainer);
		Trainer trainerSearch = trainerService.findTrainerByMail(trainer.getMail());
		
		Certification certification = MockCertif.getCertif();
		certification.setId_trainer(trainerSearch.getId());
		certificationService.createCertification(certification);
		
		Question question = MockQuestion.getQuestionMock();
		question.setId_certif(certification.getId());
		questionService.createQuestion(question);
		
		Response response = MockResponse.getResponseMock();
		response.setId_question(question.getId());
		responseService.createResponse(response);
		
		Response responseCheck = responseService.findOne(response.getId());
		assertEquals("ids should be equals", response.getId(), responseCheck.getId());
	}

}
