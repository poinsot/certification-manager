package com.cm.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.entity.Trainer;
import com.cm.exception.TrainerAlreadyExistException;
import com.cm.repository.TrainerRepository;

@Service
public class TrainerService {
	
	@Autowired
	private TrainerRepository trainerRepository;
	
	/**
	 * @param candidate : trainer entity to register in database
	 * This function persist a new trainer in database, it requires @Transactional as we write in DB
	 */
	@Transactional
	public void registerTrainer(Trainer trainer){
		if(trainer == null){
			throw new IllegalArgumentException("trainer is null");
		}
		if(trainerRepository.findTrainerByEmailAddress(trainer.getMail()) != null){
			throw new TrainerAlreadyExistException("Trainer's Email already exist", new Throwable(trainer.getMail()));
		}
		trainer = trainerRepository.save(trainer);
	}
	
	/**
	 * @param : String correponding to email 
	 * @return Trainer entity corresponding to email parameter
	 */
	public Trainer findTrainerByMail(String email){
		//return trainerRepository.findOne(id);
		if(email == null){
			throw new IllegalArgumentException("Trainer Email is null");
		}
		
		return trainerRepository.findTrainerByEmailAddress(email);
	}
	
	/**
	 * 
	 * @param String activation_code corresponding to the URL sent by email to Trainer 
	 * @return The trainer that matches the activation_code
	 */
	public Trainer findByValidationCode(String activation_code){
		if(activation_code == null){
			throw new IllegalArgumentException("Activation_code is null");
		}
		return trainerRepository.findByValidationCode(activation_code);
		
	}
	
	/**
	 * 
	 * @param : Trainer entity to persist in DB
	 * The trainer field inscription_validate is update to 1 before persistence
	 * Requires @Transactional because we persist in database
	 */
	@Transactional
	public void updateInscriptionValidateOfACandidate(Trainer trainer){
		if(trainer == null){
			throw new IllegalArgumentException("Activation_code is null");
		}
		else {
			trainer.setInscription_validate(1);
			trainerRepository.save(trainer);
		}
	}
	
	public Trainer findById(String id){
		if(id == null){
			throw new IllegalArgumentException("id is null");
		}
		Integer idNumber;
		try{
			idNumber = Integer.valueOf(id);
		}
		catch(NumberFormatException e){
			throw new IllegalArgumentException("id not a number");
		}
		return trainerRepository.findOne(idNumber);
		
	}
	
}
