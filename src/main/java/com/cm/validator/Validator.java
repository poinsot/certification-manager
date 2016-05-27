package com.cm.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validator {
	
	/**
	 * validate mail
	 * @param mail
	 * @return true if the mail's format is ok
	 * false otherwise
	 */
	public static boolean validateMail(String mail) {
		return mail != null && mail.matches("^[A-Za-z0-9_\\+\\-]+(\\.[A-Za-z0-9_\\-]+)*@[A-Za-z0-9_\\-]+(\\.[A-Za-z0-9_\\-]+)*\\.[A-Za-z0-9_\\-]{2,4}$");
	}


	/**
	 * validate id_card_number
	 * @param id_card_number
	 * @return true if the id_card_number's format is ok
	 * false otherwise
	 */
	public static boolean validateIdCardNumber(String id_card_number) {
		if(id_card_number == null){
			return false;
		}
		boolean is12charac   = id_card_number.length() == 12;
		boolean hasSpecial   = id_card_number.matches("[0-9]*");
		if(!is12charac || !hasSpecial){
			return false;
		}
		return true;
	}

	/**
	 * validate pwd
	 * @param pwd
	 * @return true if the pwd's format is ok
	 * false otherwise
	 */
	public static boolean validatePwd(String password) {
		if(password == null){
			return false;
		}
		boolean isAtLeast8charac = password.length() >= 8;
		boolean hasSpecial = password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
		boolean onlyAutorizedCharacter = password.matches("[A-Za-z0-9_-]*");
		
		if(!isAtLeast8charac || !hasSpecial || !onlyAutorizedCharacter){
			return false;
		}

		return true;
	}

	/**
	 * validate firstname
	 * @param firstname
	 * @return true if the firstname's format is ok
	 * false otherwise
	 */
	public static boolean validateFirstName(String firstname) {
		return firstname != null && firstname.trim().length() >= 1 && firstname.matches("[A-Za-z]*");
	}

	/**
	 * validate lastname
	 * @param lastname
	 * @return true if the lastname's format is ok
	 * false otherwise
	 */
	public static boolean validateLastName(String lastname) {
		return lastname != null && lastname.trim().length() >= 1 && lastname.matches("[A-Za-z]*");
	}


	/**
	 * validate birthDate
	 * @param birthDate
	 * @return true if the birthDate's format is ok
	 * false otherwise
	 */
	public static boolean validateBirthDate(String birthDate) {
		
		return birthDate != null && dateValidator(birthDate, "dd/MM/yyyy");
	}

	/**
	 * check if a date (type string) match a pattern (String)
	 * @param birthDate
	 * @return true if the date match the pattern
	 * false otherwise
	 */
	private static boolean dateValidator(String dateToValidate, String pattern) {
		if(dateToValidate == null){
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setLenient(false);
		
		try {
			
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			//System.out.println(date);
		
		} catch (ParseException e) {
			
			return false;
		}
		
		return true;
	}
		

}
