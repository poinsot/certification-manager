package com.cm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cm.CertificationManagerApplication;
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
	
	private Trainer getTrainerMock(){

		Trainer trainer = new Trainer();
		trainer.setLastname("u");
		trainer.setFirstname("u");;
		trainer.setMail("aze@rty.uiop");
		trainer.setPwd("Azer7yui");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse("1991-05-07");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trainer.setBirthdate(date);
		return trainer;
		
	}
	
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
		Trainer trainer = getTrainerMock();
		exception.expect(TrainerAlreadyExistException.class);
		trainerService.registerTrainer(trainer);
		trainerService.registerTrainer(trainer);
	}
	
	
	@Test
	public void testTrainerCreate(){
		Trainer trainer = getTrainerMock();
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
		Trainer trainer = getTrainerMock();
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
	 * should get 1 row => list<Trainer>.size() == 1 and verification that the entity is loaded
	*/
	@Test
	public void testFindByValidateCode(){
		Trainer trainer = getTrainerMock();
		String validationCode = trainer.getValidation_code();

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
	 * should get 1 row => list<Trainer>.size() == 1 and verification that the entity is loaded and the update is done
	 */
	@Test
	public void testUpdateInscriptionValidateOfATrainer(){
		Trainer trainer = getTrainerMock();
		String validationCode = trainer.getValidation_code();

		trainerService.registerTrainer(trainer);
		trainerService.updateInscriptionValidateOfACandidate(trainer);
		assertEquals("inscription_validate should be 1", Integer.valueOf(1), trainer.getInscription_validate());
		assertTrue("Should find trainer by email but trainer is null ",(trainer != null));
	}	
}
