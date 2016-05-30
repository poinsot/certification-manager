package com.cm.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void dateParserTest() {
		assertEquals("Should return null if date doesn't match format",null,DateUtils.dateParser("23/12/2012", "dd.MM.YY"));
		assertTrue("Should return Date Format if pattern matches input", DateUtils.dateParser("22/02/1983", "dd/MM/YY") instanceof Date);
	}
}
