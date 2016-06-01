package com.cm.service;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cm.CertificationManagerApplication;
import com.cm.MockObject.MockCandidate;
import com.cm.entity.Candidate;
import com.cm.exception.CandidateAlreadyExistException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=CertificationManagerApplication.class)
@Transactional 
public class CandidateServiceTest {

	@Autowired
	CandidateService candidateService;


	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	
	

	/** 
	 * get IllegalArgumentException if calling registerCandidate from CandidateService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void SavingCandidateWithParamNull(){
		candidateService.registerCandidate(null);
	}

	/**
	 * expect a CandidateAlreadyExistException
	 */
	@Test
	public void TryToRegisterCandidateWhereIdCardNumberAlreadyExistInDataBase(){
		Candidate candidate = MockCandidate.getMockCandidate();
		exception.expect(CandidateAlreadyExistException.class);
		candidateService.registerCandidate(candidate);
		candidateService.registerCandidate(candidate);
	}

	/**
	 * expect a CandidateAlreadyExistException
	 */
	@Test
	public void TryToRegisterCandidateWhereMailAlreadyExistInDataBase(){
		Candidate candidate = MockCandidate.getMockCandidate();
		
		exception.expect(CandidateAlreadyExistException.class);
		candidateService.registerCandidate(candidate);
		candidateService.registerCandidate(candidate);
	}



	
	@Test
	public void testCandidateCreate(){
		Candidate candidate = MockCandidate.getMockCandidate();
		
		candidateService.registerCandidate(candidate);

		Candidate candidateVerif =  candidateService.findByEmail(candidate.getMail());
		assertEquals("should be aze@rty.uiop", candidate.getMail(), candidateVerif.getMail());
		assertEquals("should be 231456789012", candidate.getId_card_number(), candidateVerif.getId_card_number());
	}


	/**
	 * get IllegalArgumentException if calling findByEmail from CandidateService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getCandidateByMailWithParamNull(){
		candidateService.findByEmail(null);
	}

	/**
	 * test find a candidate by his Email number
	 * should get 1 row => list<Candidate>.size() == 1 and verification that the entity is loaded
	 */
	@Test
	public void testFindByMail(){
		Candidate candidate = MockCandidate.getMockCandidate();
		candidateService.registerCandidate(candidate);
		Candidate candidateVerif = candidateService.findByEmail(candidate.getMail());
		assertEquals("should be " + candidate.getMail(), candidate.getMail(), candidateVerif.getMail());
		assertEquals("should be " + candidate.getId_card_number(), candidate.getId_card_number(), candidateVerif.getId_card_number());
	}


	/**
	 * get IllegalArgumentException if calling findByIdCard from CandidateService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getCandidateByIdCardWithParamNull(){
		candidateService.findByIdCard(null);
	}
	
	/**
	 * test find a candidate by his Id card number
	 * should get 1 row => list<Candidate>.size() == 1 and verification that the entity is loaded
	 */
	@Test
	public void testFindByIdCard(){
		Candidate candidate = MockCandidate.getMockCandidate();

		candidateService.registerCandidate(candidate);
		Candidate candidateVerif = candidateService.findByIdCard(candidate.getId_card_number());
		assertEquals("should be " + candidate.getMail(), candidate.getMail(), candidateVerif.getMail());
		assertEquals("should be " + candidate.getId_card_number(), candidate.getId_card_number(), candidateVerif.getId_card_number());
	}
	
	

	/**
	 * get IllegalArgumentException if calling findByActivationCode from CandidateService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getCandidateByActivationCodeWithParamNull(){
		candidateService.findByValidationCode(null);
	}
	
	
	/**
	 * test find a candidate by his validation Code
	 * should get 1 row => list<Candidate>.size() == 1 and verification that the entity is loaded
	*/
	@Test
	public void testFindByValidateCode(){
		Candidate candidate = MockCandidate.getMockCandidate();
		String validationCode = candidate.getValidation_code();

		candidateService.registerCandidate(candidate);
		Candidate  candidateVerif = candidateService.findByValidationCode(validationCode);
		assertEquals("should be " + validationCode, validationCode, candidateVerif.getValidation_code());
	}
	
	/**
	 * get IllegalArgumentException if calling findByActivationCode from CandidateService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void updateInscriptionValidateOfACandidateWithParamNull(){
		candidateService.updateInscriptionValidateOfACandidate(null);;
	}
	
	
	/**
	 * test Update a candidate in database with his inscription_validate to 1 
	 * should get 1 row => list<Candidate>.size() == 1 and verification that the entity is loaded and the update is done
	 */
	@Test
	public void testUpdateInscriptionValidateOfACandidate(){
		Candidate candidate = MockCandidate.getMockCandidate();
		String validationCode = candidate.getValidation_code();

		candidateService.registerCandidate(candidate);
		candidateService.updateInscriptionValidateOfACandidate(validationCode);
		Candidate candidateVerif = candidateService.findByValidationCode(validationCode);
		assertEquals("should be " + validationCode, validationCode, candidateVerif.getValidation_code());
		assertEquals("inscription_validate should be 1", Integer.valueOf(1), candidateVerif.getInscription_validate());
	}

}
