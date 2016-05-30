package com.cm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * check if a date (type string) match a pattern (String)
 * @param String dateToParse = the input date to be parsed
 * @param String pattern = the pattern used to parse the date
 * @return Date object containing the date's String parsed
 * 			null if date doesn't exist, if pattern doesn't match
 */
public class DateUtils {

	public static Date dateParser(String dateToParse, String pattern) {
		// TODO Check existing date manipulation functions
		Date date;
		if(dateToParse == null){
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setLenient(false);
		
		try {
			
			//if not valid, it will throw ParseException
			date = sdf.parse(dateToParse);
			//System.out.println(date);
		
		} catch (ParseException e) {
			
			return null;
		}
		return date;
		
	}
}
