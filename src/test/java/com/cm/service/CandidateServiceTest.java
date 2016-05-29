package com.cm.service;

import static org.junit.Assert.assertEquals;

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
	
	
	private Candidate getMockCandidate(){

		Candidate candidate = 
				new Candidate();
		candidate.setLastname("u");
		candidate.setFirstname("u");;
		candidate.setId_card_number("231456789012");
		candidate.setMail("aze@rty.uiop");
		candidate.setPwd("Azer7yui");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse("1991-05-07");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		candidate.setBirthdate(date);
		return candidate;
		
	}

	/** 
	 * get IllegalArgumentException if calling registerCandidate from CandidateService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void SavingCandidateWithParamNull(){
		candidateService.registerCandidate(null);
	}

	@Test
	public void TryToRegisterCandidateWhereIdCardNumberAlreadyExistInDataBase(){
		Candidate candidate = getMockCandidate();
		exception.expect(CandidateAlreadyExistException.class);
		candidateService.registerCandidate(candidate);
		candidateService.registerCandidate(candidate);
	}

	@Test
	public void TryToRegisterCandidateWhereMailAlreadyExistInDataBase(){
		Candidate candidate = getMockCandidate();
		
		exception.expect(CandidateAlreadyExistException.class);
		candidateService.registerCandidate(candidate);
		candidateService.registerCandidate(candidate);
	}



	@Test
	public void testCandidateCreate(){
		Candidate candidate = getMockCandidate();
		
		candidateService.registerCandidate(candidate);

		List<Candidate>  listC = candidateService.findByEmail(candidate.getMail());
		assertEquals("size of array should be 0 and is " + listC.size(), 1, listC.size());
		assertEquals("should be aze@rty.uiop", candidate.getMail(), listC.get(0).getMail());
		assertEquals("should be 231456789012", candidate.getId_card_number(), listC.get(0).getId_card_number());
	}


	/**
	 * get IllegalArgumentException if calling findByEmail from CandidateService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getCandidateByMailWithParamNull(){
		candidateService.findByEmail(null);
	}

	/**
	 * test if find a candidate by his Email number
	 * should get 1 row => list<Candidate>.size() == 1 and verification that the entity is loaded
	 * @throws ParseException 
	 */
	@Test
	public void testFindByMail(){
		Candidate candidate = getMockCandidate();
		candidateService.registerCandidate(candidate);
		List<Candidate>  listC = candidateService.findByEmail(candidate.getMail());
		assertEquals("size of array should be 1 and is " + listC.size(), 1, listC.size());
		Candidate candidateVerif = listC.get(0);
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
	 * test if find a candidate by his Id card number
	 * should get 1 row => list<Candidate>.size() == 1 and verification that the entity is loaded
	 * @throws ParseException 
	 */
	@Test
	public void testFindByIdCard(){
		Candidate candidate = getMockCandidate();

		candidateService.registerCandidate(candidate);
		List<Candidate>  listC = candidateService.findByIdCard(candidate.getId_card_number());
		assertEquals("size of array should be 1 and is " + listC.size(), 1, listC.size());
		Candidate candidateVerif = listC.get(0);
		assertEquals("should be " + candidate.getMail(), candidate.getMail(), candidateVerif.getMail());
		assertEquals("should be " + candidate.getId_card_number(), candidate.getId_card_number(), candidateVerif.getId_card_number());
	}
	
	

	

}
