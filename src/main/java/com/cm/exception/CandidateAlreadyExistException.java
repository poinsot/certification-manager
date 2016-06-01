package com.cm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CandidateAlreadyExistException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4586606860187430912L;


	public CandidateAlreadyExistException(String string) {
		super(string);
	}
	
	
	public CandidateAlreadyExistException(String string, Throwable cause) {
		super(string, cause);
	}
}
