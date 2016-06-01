package com.cm.MockObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cm.entity.Trainer;

public class MockTrainer {

	public static Trainer getTrainerMock(){

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
			e.printStackTrace();
		}
		trainer.setBirthdate(date);
		return trainer;
		
	}
}
