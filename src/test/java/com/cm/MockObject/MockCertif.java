package com.cm.MockObject;

import java.io.File;
import java.io.IOException;

import com.cm.entity.Certification;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MockCertif {
	
	public static Certification getCertif(){
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(System.getProperty("user.dir"));
			return  mapper.readValue(new File("src/main/resources/jsonexemple.json"), Certification.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
