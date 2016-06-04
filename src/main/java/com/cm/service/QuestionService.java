package com.cm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Service;

import com.cm.CertificationManagerApplication;
import com.cm.entity.Certification;
import com.cm.entity.Question;
import com.cm.exception.CertificationNotFoundException;
import com.cm.repository.CertificationRepository;
import com.cm.repository.QuestionRepository;

@Service
@SpringApplicationConfiguration(classes=CertificationManagerApplication.class)
public class QuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	CertificationRepository certificationRepository;


	@Transactional
	public Question createQuestion(Question question) {
		if(question == null){
			throw new IllegalArgumentException("question is null");
		}
		
		if(question.getId_certif() == null){
			throw new IllegalArgumentException("id_certif of question is null");
		}
		if(question.getText() == null){
			throw new IllegalArgumentException("Text of question is null");
		}
		
		if(certificationRepository.findOne(question.getId_certif()) == null){
			throw new CertificationNotFoundException();
		}
		return questionRepository.save(question);
	}
	

	public List<Question> findAllQuestionForACertification(Integer id_certif) {
		if(id_certif == null){
			throw new IllegalArgumentException("id_certif of question is null");
		}
		Certification certif = certificationRepository.findOne(id_certif);
		if( certif == null){
			throw new CertificationNotFoundException();
		}
		return questionRepository.findAllQuestionForACertification(id_certif);
	}
}
