package com.cm.exception;

public class TrainerAlreadyExistException extends RuntimeException {

	public TrainerAlreadyExistException(String string) {
		super(string);
	}
	
	
	public TrainerAlreadyExistException(String string, Throwable cause) {
		super(string, cause);
	}
}
