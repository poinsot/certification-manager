package com.cm.MockObject;

import com.cm.entity.Question;

public class MockQuestion {
	
	public static Question getQuestionMock(){

		Question question = new Question();
		question.setText("Ceci la question 1 ?");
		return question;
		
	}

}
