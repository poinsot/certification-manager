package com.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cm.CertificationManagerApplication;
import com.cm.controller.CandidateController;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=CertificationManagerApplication.class)
public class CandidateControllerTest {

	@Test
	public void registerFormCandidateTest() {
		CandidateController candidateController = new CandidateController();
		assertEquals("should return \"candidateRegister\"","candidateRegister", candidateController.registerFormCandidate());
		
	}
	

}
