package com.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class MainNavigationController {

	//private static Logger LOGGER = Logger.getLogger(MainNavigationController.class.getName()); 
	
	@RequestMapping(path="/", 
					method={RequestMethod.GET})
	public String callHomePage() {
		//LOGGER.info("TestOK");
		return "home";
	}
	
	
}