package com.cm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cm.CertificationManagerApplication;
import com.cm.MockObject.MockTrainer;
import com.cm.entity.Trainer;
import com.cm.exception.TrainerAlreadyExistException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=CertificationManagerApplication.class)
@Transactional 
public class TrainerServiceTest {

	@Autowired
	TrainerService trainerService;

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	
	
	/** 
	 * get IllegalArgumentException if calling registerTrainer from TrainerService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void SavingTrainerWithParamNull(){
		trainerService.registerTrainer(null);
	}
	
	/**
	 * expect a TrainerAlreadyExistException if entity already exist in database
	 */
	@Test
	public void TryToRegisterTrainerWhereMailAlreadyExistInDataBase(){
		Trainer trainer = MockTrainer.getTrainerMock();
		exception.expect(TrainerAlreadyExistException.class);
		trainerService.registerTrainer(trainer);
		trainerService.registerTrainer(trainer);
	}
	
	
	@Test
	public void testTrainerCreate(){
		Trainer trainer = MockTrainer.getTrainerMock();
		trainerService.registerTrainer(trainer);

		Trainer testTrainer  = trainerService.findTrainerByMail(trainer.getMail());
		assertTrue("Should find trainer by email but trainer is null ",testTrainer != null);
		assertEquals("should be aze@rty.uiop", trainer.getMail(), testTrainer.getMail());
	}

	/**
	 * get IllegalArgumentException if calling findByEmail from TrainerService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getTrainerByMailWithParamNull(){
		trainerService.findTrainerByMail(null);
	}

	/**
	 * test find a Trainer by his Email number
	 * should get 1 row => list<Trainer>.size() == 1 and verification that the entity is loaded
	 */
	@Test
	public void testFindByMail(){
		Trainer trainer = MockTrainer.getTrainerMock();
		trainerService.registerTrainer(trainer);
		Trainer testTrainer = trainerService.findTrainerByMail(trainer.getMail());
		assertTrue("Should find trainer by email but trainer is null ",(testTrainer != null));
		assertEquals("should be aze@rty.uiop", trainer.getMail(), testTrainer.getMail());
		
	}

		

	/**
	 * get IllegalArgumentException if calling findByActivationCode from TrainerService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getTrainerByActivationCodeWithParamNull(){
		trainerService.findTrainerByMail(null);
	}
	
	
	/**
	 * test find a Trainer by his validation Code
	 */
	@Test
	public void testFindByValidateCode(){
		Trainer trainer = MockTrainer.getTrainerMock();
		trainer.getValidation_code();

		trainerService.registerTrainer(trainer);
		Trainer testTrainer = trainerService.findByValidationCode(trainer.getValidation_code());
		assertEquals("should be aze@rty.uiop", trainer.getMail(), testTrainer.getMail());
		assertTrue("Should find trainer by email but trainer is null ",(testTrainer != null));
	}
	
	/**
	 * get IllegalArgumentException if calling findByActivationCode from TrainerService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void updateInscriptionValidateOfATrainerWithParamNull(){
		trainerService.updateInscriptionValidateOfACandidate(null);
	}
	
	
	/**
	 * test Update a Trainer in database with his inscription_validate to 1 
	 */
	@Test
	public void testUpdateInscriptionValidateOfATrainer(){
		Trainer trainer = MockTrainer.getTrainerMock();
		trainer.getValidation_code();

		trainerService.registerTrainer(trainer);
		trainerService.updateInscriptionValidateOfACandidate(trainer);
		assertEquals("inscription_validate should be 1", Integer.valueOf(1), trainer.getInscription_validate());
		assertTrue("Should find trainer by email but trainer is null ",(trainer != null));
	}
	
	/**
	 * get IllegalArgumentException if calling findById from TrainerService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void findIdWithParamNull(){
		trainerService.findById(null);
	}
	
	/**
	 * test find a Trainer by his id
	 */
	@Test
	public void testFindById(){
		Trainer trainer = MockTrainer.getTrainerMock();
		
		trainerService.registerTrainer(trainer);
		Trainer testTrainer = trainerService.findById(String.valueOf(trainer.getId()));
		assertEquals("should be aze@rty.uiop", trainer.getMail(), testTrainer.getMail());
		assertEquals("should be " + trainer.getId(), trainer.getId(), testTrainer.getId());
		
		
	}
}