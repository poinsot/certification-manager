package com.cm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TrainerAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3970574083008123140L;


	public TrainerAlreadyExistException(String string) {
		super(string);
	}
	
	
	public TrainerAlreadyExistException(String string, Throwable cause) {
		super(string, cause);
	}
}
