package com.cm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class QuestionNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3844613623633076061L;

}
