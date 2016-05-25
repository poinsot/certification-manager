package com.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cm.controller.HomeNavigationController;


/**
 * 
 * @author Glenn JUDEAU
 * Test HomeNavigationController class
 * HomeNavigationController.callHomePage must return a String and this String must be "home"
 */

public class HomeNavigationControllerTest {

	@Test
	public void HomeNavigationControllerTest() {
		assertTrue("Return is type String", HomeNavigationController.callHomePage() instanceof String);
		assertEquals("Return of HomeNavigation is String=home ","home",HomeNavigationController.callHomePage());
	}

}
