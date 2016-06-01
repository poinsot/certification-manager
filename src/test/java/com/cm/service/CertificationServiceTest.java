package com.cm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cm.CertificationManagerApplication;
import com.cm.MockObject.MockCertif;
import com.cm.entity.Certification;
import com.cm.exception.TrainerNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=CertificationManagerApplication.class)
@Transactional 
public class CertificationServiceTest {

	@Autowired
	CertificationService certificationService;
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateCertifWithCertifNull() {
		certificationService.createCertification(null);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void testCreateCertifWithIdTrainerNull() {
		Certification certification = MockCertif.getCertif();
		certificationService.createCertification(certification);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateCertifWithTitleNull() {
		Certification certification = MockCertif.getCertif();
		certification.setTitle(null);
		certificationService.createCertification(certification);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateCertifWithDurationNull() {
		Certification certification = MockCertif.getCertif();
		certification.setDuration(null);
		certificationService.createCertification(certification);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateCertifWithNbQuestNull() {
		Certification certification = MockCertif.getCertif();
		certification.setNb_question(null);
		certificationService.createCertification(certification);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateCertifWithPercentSuccessNull() {
		Certification certification = MockCertif.getCertif();
		certification.setPercent_success(null);
		certificationService.createCertification(certification);
	}
	
	@Test(expected=TrainerNotFoundException.class)
	public void testCreateCertificationWithoutTrainer(){
		Certification certification = MockCertif.getCertif();
		certification.setId_trainer(1);
		certificationService.createCertification(certification);
	}
	
	@Test
	public void testCreateCertif(){
		Certification certification = MockCertif.getCertif();
		certification.setId_trainer(36);
		certificationService.createCertification(certification);
	}

}
