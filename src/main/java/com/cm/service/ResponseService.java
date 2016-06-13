package com.cm.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Service;
import com.cm.CertificationManagerApplication;
import com.cm.entity.Response;
import com.cm.exception.QuestionNotFoundException;
import com.cm.repository.QuestionRepository;
import com.cm.repository.ResponseRepository;


@Service
@SpringApplicationConfiguration(classes=CertificationManagerApplication.class)
public class ResponseService {
	
	@Autowired
	ResponseRepository responseRepository;
	
	@Autowired
	QuestionRepository questionRepository;

	@Transactional
	public Response createResponse(Response response) {
		if(response == null){
			throw new IllegalArgumentException("response is null");
		}
		
		if(response.getId_question() == null){
			throw new IllegalArgumentException("id_question of response is null");
		}
		if(response.getText() == null){
			throw new IllegalArgumentException("Text of response is null");
		}
		
		if(response.getIs_correct() == null){
			throw new IllegalArgumentException("is_correct of response is null");
		}
		
		if(questionRepository.findOne(response.getId_question()) == null){
			throw new QuestionNotFoundException();
		}
		return responseRepository.save(response);
	}
	
	@Transactional
	public Response findOne(Integer id) {
		if(id == null){
			throw new IllegalArgumentException("id is null");
		}
		return responseRepository.findOne(id);
	}
	

}
