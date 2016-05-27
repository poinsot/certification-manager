package com.cm.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cm.CertificationManagerApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CertificationManagerApplication.class)
public class ValidatorTest {


	@Test
	public void validatePwd(){
		assertFalse("expect False, length < 8", Validator.validatePwd("1Zpaz"));
		assertFalse("expect False, null", Validator.validatePwd(null));
		assertFalse("expect False, miss digit", Validator.validatePwd("Zpazerty"));
		assertFalse("expect False, miss maj", Validator.validatePwd("1pazerty"));
		assertFalse("expect False, miss maj, miss digit", Validator.validatePwd("vejenzerty"));
		assertFalse("expect False, miss maj, miss digit and length < 8", Validator.validatePwd("zerty"));
		assertFalse("expect False, unautorizedCaracter", Validator.validatePwd("èfdL8zerty"));
		assertTrue("expect True", Validator.validatePwd("Z1azerty"));		  
	}

	@Test
	public void validateIdCard(){
		assertFalse("expect False, length < 12", Validator.validateIdCardNumber("123456"));
		assertFalse("expect False, null", Validator.validateIdCardNumber(null));
		assertFalse("expect False, length > 12", Validator.validateIdCardNumber("1234567890123"));
		assertFalse("expect False, one or more charac != number", Validator.validateIdCardNumber("12345678901r"));
		assertTrue("expect True", Validator.validateIdCardNumber("123456789012"));		  
	}

	@Test
	public void validateMail(){
		assertFalse("expect False, no @ character", Validator.validateMail("Abc.example.com"));
		assertFalse("expect False, null", Validator.validateMail(null));

		assertFalse("expect False, only one @ is allowed outside quotation marks", Validator.validateMail("A@b@c@example.com"));

		assertFalse("expect False, one of the special characters in this local part are allowed outside quotation marks", Validator.validateMail("a\"b(c)d,e:f;g<h>i[j\\k]l@example.com"));
		assertFalse("expect False, quoted strings must be dot separated or the only element making up the local part", Validator.validateMail("just\"not\"right@example.com"));
		assertFalse("expect False, spaces, quotes, and backslashes may only exist when within quoted strings and preceded by a backslash", Validator.validateMail("his is\"not\\allowed@example.com"));
		assertFalse("expect False, even if escaped (preceded by a backslash), spaces, quotes, and backslashes must still be contained by quotes", Validator.validateMail("this\\ still\\\"not\\\\allowed@example.com"));
		assertFalse("expect False, double dot before @", Validator.validateMail("john..doe@example.com"));
		assertFalse("expect False, double dot after @", Validator.validateMail("john.doe@example..com"));
		assertTrue("expect True", Validator.validateMail("jenkins@hj.hui"));		  
	}

	@Test
	public void validateFirstName(){
		assertFalse("expect False, length < 1", Validator.validateFirstName(""));
		assertFalse("expect False, null", Validator.validateFirstName(null));
		assertFalse("expect False, one or more charac != letter", Validator.validateFirstName("fjezf5"));
		assertTrue("expect True", Validator.validateFirstName("Val"));		  
		assertTrue("expect True", Validator.validateFirstName("V"));
		assertTrue("expect True", Validator.validateFirstName("v"));	
	}

	@Test
	public void validateLastName(){
		assertFalse("expect False, length < 1", Validator.validateLastName(""));
		assertFalse("expect False, null", Validator.validateLastName(null));
		assertFalse("expect False, one or more charac != letter", Validator.validateLastName("fjezf5"));
		assertTrue("expect True", Validator.validateLastName("Val"));		  
		assertTrue("expect True", Validator.validateLastName("V"));
		assertTrue("expect True", Validator.validateLastName("v"));	
	}
	
	@Test
	public void validateBirthDate(){
		assertFalse("expect False, null", Validator.validateBirthDate(null));
		assertFalse("expect False, day < 0", Validator.validateBirthDate("-1/02/1991"));
		assertFalse("expect False, days > days on month", Validator.validateBirthDate("32/02/1991"));
		assertFalse("expect False, month < 0", Validator.validateBirthDate("01/-2/1991"));
		assertFalse("expect False, month > 12", Validator.validateBirthDate("05/13/1991"));
		assertFalse("expect False, month < 0", Validator.validateBirthDate("01/02/-199"));
		
		assertFalse("expect False, not bissextile", Validator.validateBirthDate("29/02/2011"));
		assertFalse("expect False, 30 days in April", Validator.validateBirthDate("31/04/2012"));
		
		assertTrue("expect True", Validator.validateBirthDate("29/02/2012"));	
		assertTrue("expect True", Validator.validateBirthDate("2/2/1991"));		  
	}

}
