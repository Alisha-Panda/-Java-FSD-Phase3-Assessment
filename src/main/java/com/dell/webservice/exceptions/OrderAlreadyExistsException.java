package com.dell.webservice.exceptions;

public class OrderAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	String message;

	public OrderAlreadyExistsException(String message) {
		super(message);
	}

}
