package com.cm.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class RandomCodeGenerator {

	/**
	 * Generate random alphanumeric code.
	 * @param length of the generated random code
	 * @return a String of alphanumeric characters
	 * @throws IllegalArgumentException if the length is not in the 1-64 interval
	 */
	public String generateCode(int length) {
		if (length <= 0 || length >= 65) {
			throw new IllegalArgumentException("length should be in the 1-64 interval");
		}
		return RandomStringUtils.randomAlphanumeric(length);
	}

}
