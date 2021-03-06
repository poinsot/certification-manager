package com.cm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 *
 * Error Navigation Controller 
 * The purpose of this class is to control the errors Pages
 */

@Controller
@RequestMapping("/error")
public class ErrorController {


	@RequestMapping(path="/database",
			method={RequestMethod.GET})
	public static String callErrorDatabase() {
		return "error_database";
	}	
	@RequestMapping(path="/alreadyexist",
			method={RequestMethod.GET})
	public static String candidateAlreadyExist() {
		return "error_alreadyexist";
	}
	
	@RequestMapping(path="/tr_alreadyexist",method={RequestMethod.GET})
	public static String trainerAlreadyExist() {
		return "trainer_error_alreadyexist";
	}
	
	@RequestMapping(path="/confirmationcodeinvalide",
			method={RequestMethod.GET})
	public static String candidateDontExist() {
		return "error_confirmation_code_invalide";
	}
	
		
}