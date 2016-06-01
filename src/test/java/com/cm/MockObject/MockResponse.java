package com.cm.MockObject;


import com.cm.entity.Response;

public class MockResponse {
	
	public static Response  getResponseMock(){

		Response  response  = new Response();
		response.setText("Ceci la response 1 ?");
		response.setId_question(1);
		response.setIs_correct(0);
		return response;
		
	}

}
