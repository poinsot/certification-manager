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

	/** 
	 * get IllegalArgumentException if calling registerCandidate from CandidateService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void SavingCandidateWithParamNull(){
		candidateService.registerCandidate(null);
	}

	@Test
	public void TryToRegisterCandidateWhereIdCardNumberAlreadyExistInDataBase(){
		Candidate candidate = new Candidate();
		candidate.setFirstname("z");
		candidate.setLastname("z");
		candidate.setId_card_number("123456789012");
		candidate.setMail("jenkins@hj.hdddui");
		candidate.setBirthdate(new Date());
		candidate.setPwd("Azer7yuiop");
		exception.expect(CandidateAlreadyExistException.class);
		candidateService.registerCandidate(candidate);
		candidateService.registerCandidate(candidate);
	}

	@Test
	public void TryToRegisterCandidateWhereMailAlreadyExistInDataBase(){
		Candidate candidate = new Candidate();
		candidate.setFirstname("z");
		candidate.setLastname("z");
		candidate.setId_card_number("312456789012");
		candidate.setMail("jenkins@hj.hui");
		candidate.setBirthdate(new Date());
		candidate.setPwd("Azer7yuiop");
		exception.expect(CandidateAlreadyExistException.class);
		candidateService.registerCandidate(candidate);
		candidateService.registerCandidate(candidate);
	}



	@Test
	public void testCandidateCreate() throws Exception{

		Candidate candidate = 
				new Candidate();
		candidate.setLastname("u");
		candidate.setFirstname("u");;
		candidate.setId_card_number("231456789012");
		candidate.setMail("aze@rty.uiop");
		candidate.setPwd("Azer7yui");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("1991-05-07");
		candidate.setBirthdate(date);

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
	 * should get 1 row => list<Candidate>.size() == 1 and verification that the entity is loaded
	 * @throws ParseException 
	 */
	@Test
	public void testFindByMail() throws ParseException{
		Candidate candidate = 
				new Candidate();
		candidate.setLastname("u");
		candidate.setFirstname("u");;
		candidate.setId_card_number("123456789123");
		candidate.setMail("jenkins@hj.hui");
		candidate.setPwd("Azer7yui");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("1991-05-07");
		candidate.setBirthdate(date);

		candidateService.registerCandidate(candidate);
		List<Candidate>  listC = candidateService.findByEmail("jenkins@hj.hui");
		assertEquals("size of array should be 1 and is " + listC.size(), 1, listC.size());
		Candidate candidateVerif = listC.get(0);
		assertEquals("should be jenkins@hj.hui", "jenkins@hj.hui", candidateVerif.getMail());
		assertEquals("should be 123456789123", "123456789123", candidateVerif.getId_card_number());
	}


	/**
	 * get IllegalArgumentException if calling findByIdCard from CandidateService with param null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getCandidateByIdCardWithParamNull(){
		candidateService.findByIdCard(null);
	}
	
	/**
	 * should get 1 row => list<Candidate>.size() == 1 and verification that the entity is loaded
	 * @throws ParseException 
	 */
	@Test
	public void testFindByIdCard() throws ParseException{
		Candidate candidate = 
				new Candidate();
		candidate.setLastname("u");
		candidate.setFirstname("u");;
		candidate.setId_card_number("123456789123");
		candidate.setMail("jenkins@hj.hui");
		candidate.setPwd("Azer7yui");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("1991-05-07");
		candidate.setBirthdate(date);

		candidateService.registerCandidate(candidate);
		List<Candidate>  listC = candidateService.findByIdCard("123456789123");
		assertEquals("size of array should be 1 and is " + listC.size(), 1, listC.size());
		Candidate candidateVerif = listC.get(0);
		assertEquals("should be jenkins@hj.hui", "jenkins@hj.hui", candidateVerif.getMail());
		assertEquals("should be 123456789123", "123456789123", candidateVerif.getId_card_number());
	}

}
