package com.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cm.entity.Trainer;


public interface TrainerRepository extends JpaRepository <Trainer, Integer> {
	
	
	public final static String FIND_TRAINER_BY__EMAIL_ADDRESS_QUERY = "SELECT t FROM #{#entityName} t WHERE t.mail = ?1";
	public final static String FIND_TRAINER_BY_VALIDATION_CODE_QUERY = "SELECT t FROM #{#entityName} t WHERE t.validation_code = ?1";	
	
	
	@Query(FIND_TRAINER_BY__EMAIL_ADDRESS_QUERY)
	Trainer findTrainerByEmailAddress(String emailAddress);

	@Query(FIND_TRAINER_BY_VALIDATION_CODE_QUERY)
	Trainer findByValidationCode(String activation_code);
	
}
