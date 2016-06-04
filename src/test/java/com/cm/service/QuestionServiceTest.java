package com.cm.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

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
	public void testCreateQuestionWithQuestionNull() {
		questionService.createQuestion(null);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void testCreateQuestionWithIdCertifNull() {
		Question question = MockQuestion.getQuestionMock();
		questionService.createQuestion(question);
		
	}
	
	@Test(expected=CertificationNotFoundException.class)
	public void testCreateQuestionWithCertifNotFound(){
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
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindNumberOfQuestionForTakingACertificationWithIdCertifNullNbQuestNull(){
		questionService.findAllQuestionForACertification(null);
	}
	
	@Test(expected=CertificationNotFoundException.class)
	public void testFindNumberOfQuestionForTakingACertificationWithNbQuestNull(){
		questionService.findAllQuestionForACertification(-1);
	}
	
	
	@Test
	public void testGetAllQuestion(){
		Trainer trainer = MockTrainer.getTrainerMock();
		trainer = trainerService.registerTrainer(trainer);
		Certification certif = MockCertif.getCertif();
		certif.setId_trainer(trainer.getId());
		certif = certificationService.createCertification(certif);
		List<Question> list = questionService.findAllQuestionForACertification(certif.getId());
		for (Question question : list) {
			assertTrue("should have", certif.getQuestions().contains(question));
		}
	}
	
}
