package com.cm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cm.CertificationManagerApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=CertificationManagerApplication.class)
public class RandomCodeGeneratorTest {

	@Autowired
	private RandomCodeGenerator randomCodeGenerator;

	@Test
	public void lengthIsAsGiven() {
		assertEquals(6, randomCodeGenerator.generateCode(6).length());
		assertEquals(1, randomCodeGenerator.generateCode(1).length());
		assertEquals(64, randomCodeGenerator.generateCode(64).length());
	}

	@Test
	public void isAlphanumeric() {
		String pattern = "^[a-zA-Z0-9]*$";
		String randomCode = randomCodeGenerator.generateCode(64);
		assertTrue(randomCode.matches(pattern));
	}

	@Test(expected=IllegalArgumentException.class)
	public void lengthIs0() {
		randomCodeGenerator.generateCode(0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void lengthIsNegative() {
		randomCodeGenerator.generateCode(-10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void lengthIsTooBig() {
		randomCodeGenerator.generateCode(66);
	}

}
