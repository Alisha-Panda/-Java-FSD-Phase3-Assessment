package com.dell.webservice.exceptions;

public class InvalidOrderException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	String message;

	public InvalidOrderException(String message) {
		super(message);
	}

}
