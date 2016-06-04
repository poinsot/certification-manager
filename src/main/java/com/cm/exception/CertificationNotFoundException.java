package com.cm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CertificationNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8956442002060908283L;
	
	

}
