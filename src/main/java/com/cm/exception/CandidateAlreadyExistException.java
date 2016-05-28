package com.cm.exception;

public class CandidateAlreadyExistException extends RuntimeException {
	
	public CandidateAlreadyExistException(String string) {
		super(string);
	}
	
	
	public CandidateAlreadyExistException(String string, Throwable cause) {
		super(string, cause);
	}
}
