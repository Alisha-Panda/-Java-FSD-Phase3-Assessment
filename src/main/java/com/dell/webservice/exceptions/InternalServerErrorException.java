package com.dell.webservice.exceptions;

public class InternalServerErrorException extends RuntimeException{
	private static final long serialVersionUID = 1L;
		
		String message;
	
		public InternalServerErrorException(String message) {
			super(message);
		}
}
