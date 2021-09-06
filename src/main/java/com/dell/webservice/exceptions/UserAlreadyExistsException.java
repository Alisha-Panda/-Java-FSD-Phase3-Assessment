package com.dell.webservice.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	String message;

	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
