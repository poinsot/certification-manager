package com.cm.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Service;

import com.cm.CertificationManagerApplication;
import com.cm.entity.Candidate;
import com.cm.exception.CandidateAlreadyExistException;
import com.cm.repository.CandidateRepository;

@Service
@SpringApplicationConfiguration(classes=CertificationManagerApplication.class)
public class CandidateService {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(CandidateService.class.getName());


	@Autowired
	private CandidateRepository candidateRepository;

	/**
	 * register a Candidate in database
	 * @param candidate : candidate to register in database
	 * @throws RunTimeException CandidateAlreadyExist if email OR id_card_number already exist in the database
	 * @throws IllegalArgumentException @param is null
	 */
	@Transactional
	public void registerCandidate(Candidate candidate){
		if(candidate == null){
			throw new IllegalArgumentException("candidate is null");
		}
		if(candidateRepository.findByAddress(candidate.getMail()) != null){
			throw new CandidateAlreadyExistException("Email already exist", new Throwable(candidate.getMail()));
		}
		if(candidateRepository.findByIdCard(candidate.getId_card_number()) != null){
			throw new CandidateAlreadyExistException("Id_card_number already exist", new Throwable(candidate.getId_card_number()));
		}
		
			
		candidateRepository.save(candidate);
	}
	
	
	/**
	 * find a candidate in database by his Email
	 * @param mail
	 * @return Candidate entity if database contains a row where mail match or null otherwise
	 * @throws IllegalArgumentException if param is null
	 */
	@Transactional
	public Candidate findByEmail(String mail){
		if(mail == null){
			throw new IllegalArgumentException("mail is null");
		}
		return candidateRepository.findByAddress(mail);
		
	}
	
	
	/**
	 * find a candidate in database by his Id Card
	 * @param id_card_number
	 * @return Candidate entity if database contains a row where id_card_number match or null otherwise
	 * @throws IllegalArgumentException if @param is null
	 */
	@Transactional
	public Candidate findByIdCard(String id_card_number){
		if(id_card_number == null){
			throw new IllegalArgumentException("id_card_number is null");
		}
		return candidateRepository.findByIdCard(id_card_number);
		
	}
	
	/**
	 * find a candidate in database by his code activation
	 * @param id_card_number
	 * @return Candidate entity if database contains a row where id_card_number match or null otherwise
	 * @throws IllegalArgumentException if @param is null
	 */
	@Transactional
	public Candidate findByValidationCode(String activation_code){
		if(activation_code == null){
			throw new IllegalArgumentException("activation_code is null");
		}
		return candidateRepository.findByValidationCode(activation_code);
		
	}
	
	/**
	 * Update a candidate in database with his inscription_validate to 1 
	 * @param activation_code
	 * @throws IllegalArgumentException if @param is null
	 */
	@Transactional
	public void updateInscriptionValidateOfACandidate(String activation_code){
		if(activation_code == null){
			throw new IllegalArgumentException("activation_code is null");
		}
		candidateRepository.updateInscriptionValidateOfACandidate(Integer.valueOf(1), activation_code);
		
	}

}
