package com.dell.webservice.exceptions;

public class InvalidUserException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	String message;

	public InvalidUserException(String message) {
		super(message);
	}

}
