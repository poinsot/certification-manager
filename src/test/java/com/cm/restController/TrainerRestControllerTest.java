package com.cm.restController;



import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cm.CertificationManagerApplication;
import com.cm.MockObject.MockCertif;
import com.cm.controller.TrainerRestController;
import com.cm.entity.Certification;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=CertificationManagerApplication.class)
public class TrainerRestControllerTest {

	@Autowired
	TrainerRestController trainerRestController;
	
	
	@Test
	@JsonIgnoreProperties(ignoreUnknown = true)
	public void parseJson() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Certification certif = mapper.readValue(new File("C:\\Users\\Val\\Desktop\\certification-manager\\src\\main\\resources\\jsonexemple.json"), Certification.class);
		assertEquals("", Integer.valueOf(85), certif.getPercent_success());

	}
	
	@Test
	public void testverifTitle(){
		Map<String, String> errors = new HashMap<String, String>();
		Certification certif = MockCertif.getCertif();
		assertEquals("title : bla OK", errors.isEmpty(), trainerRestController.validateCertification(certif).isEmpty());
		
		certif.setTitle(null);
		errors.put("title", null);
		assertEquals("title : null pasOK", errors.get("title"), trainerRestController.validateCertification(certif).get("title"));
		
		errors.put("title", "b");
		certif.setTitle("b");
		assertEquals("title : b pasOK", errors.get("title"), trainerRestController.validateCertification(certif).get("title"));
	}
	
	@Test
	public void testverifDescription(){
		Map<String, String> errors = new HashMap<String, String>();
		Certification certif = MockCertif.getCertif();
		assertEquals("description : bla OK", errors.isEmpty(), trainerRestController.validateCertification(certif).isEmpty());
		certif.setTitle(null);
		errors.put("description", null);
		assertEquals("title : null OK", errors.isEmpty(), trainerRestController.validateCertification(certif).isEmpty());
	}
	
	@Test
	public void testverifPercentSuccess(){
		Map<String, String> errors = new HashMap<String, String>();
		Certification certif = MockCertif.getCertif();
		assertEquals("percentSuccess : 50 OK", errors.isEmpty(), trainerRestController.validateCertification(certif).isEmpty());
		
		certif.setPercent_success(null);
		errors.put("percentSuccess", null);
		assertEquals("percent : null pasOK", errors.containsKey("percentSuccess"), trainerRestController.validateCertification(certif).containsKey("percentSuccess"));
		
		
		certif.setPercent_success(-1);
		errors.put("percentSuccess", "-1");
		assertEquals("percent : -1 pasOK", errors.get("percentSuccess"), trainerRestController.validateCertification(certif).get("percentSuccess"));
		
		certif.setPercent_success(101);
		errors.put("percentSuccess", "101");
		assertEquals("percent : 101 pasOK", errors.get("percentSuccess"), trainerRestController.validateCertification(certif).get("percentSuccess"));
	}
	
	@Test
	public void testverifNbQuest(){
		Map<String, String> errors = new HashMap<String, String>();
		Certification certif = MockCertif.getCertif();
		assertEquals("nb_quest : 30 OK", errors.isEmpty(), trainerRestController.validateCertification(certif).isEmpty());
		certif.setNb_question(null);
		errors.put("nb_quest", null);
		assertEquals("nb_quest : null pasOK", errors.containsKey("nb_quest"), trainerRestController.validateCertification(certif).containsKey("nb_quest"));
		errors.put("nb_quest", "-1");
		certif.setNb_question(-1);
		assertEquals("nb_quest : -1 pasOK", errors.get("nb_quest"), trainerRestController.validateCertification(certif).get("nb_quest"));
	}
	
	@Test
	public void testverifDuration(){
		Map<String, String> errors = new HashMap<String, String>();
		Certification certif = MockCertif.getCertif();
		assertEquals("duration : bla OK", errors.isEmpty(), trainerRestController.validateCertification(certif).isEmpty());
		certif.setDuration(null);
		errors.put("duration", null);
		assertEquals("duration : null pasOK", errors.containsKey("duration"), trainerRestController.validateCertification(certif).containsKey("duration"));
	}
}
