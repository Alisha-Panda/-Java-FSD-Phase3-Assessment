package com.dell.webservice.exceptions;

public class OrderNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	String message;

	public OrderNotFoundException(String message) {
		super(message);
	}

}
