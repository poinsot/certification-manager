package com.cm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResponseNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8487903112020280131L;

	
	

}
