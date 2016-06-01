package com.cm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cm.CertificationManagerApplication;
import com.cm.MockObject.MockCertif;
import com.cm.MockObject.MockQuestion;
import com.cm.MockObject.MockTrainer;
import com.cm.entity.Certification;
import com.cm.entity.Question;
import com.cm.entity.Trainer;
import com.cm.exception.CertificationNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=CertificationManagerApplication.class)
@Transactional 
public class QuestionServiceTest {

	@Autowired
	QuestionService questionService;
	
	@Autowired
	CertificationService certificationService;
	
	@Autowired
	TrainerService trainerService;
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateCertifWithCertifNull() {
		questionService.createQuestion(null);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void testCreateQuestionWithIdCertifNull() {
		Question question = MockQuestion.getQuestionMock();
		questionService.createQuestion(question);
		
	}
	
	@Test(expected=CertificationNotFoundException.class)
	public void testCreateCertificationWithCertifNotFound(){
		Question question = MockQuestion.getQuestionMock();
		question.setId_certif(12);
		questionService.createQuestion(question);
		
	}
	
	@Test
	public void testCreateQuestion(){
		Trainer trainer = MockTrainer.getTrainerMock();
		trainerService.registerTrainer(trainer);
		Trainer trainerSearch = trainerService.findTrainerByMail(trainer.getMail());
		
		Certification certification = MockCertif.getCertif();
		certification.setId_trainer(trainerSearch.getId());
		certificationService.createCertification(certification);
		
		Question question = MockQuestion.getQuestionMock();
		question.setId_certif(certification.getId());
		
		questionService.createQuestion(question);
	}

}
