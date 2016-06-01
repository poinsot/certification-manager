package com.cm.MockObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cm.entity.Candidate;

public class MockCandidate {

	
	public static Candidate getMockCandidate(){

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
}
