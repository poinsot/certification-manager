package com.cm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author Glenn JUDEAU
 * Home Navigation Controller 
 * The purpose of this class is to control the home Page
 */

@Controller
@RequestMapping("/")
public class HomeNavigationController {

	 /**
	  * CallHomePage mapped to "/" URL
	  * @return "home" the tag of the home.jsp to be used
	  */
	
	@RequestMapping(path="/",
					method={RequestMethod.GET})
	public static String callHomePage() {
		return "home";
	}	
}