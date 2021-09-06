package com.dell.webservice.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	ExceptionResponse response;
	
	@ExceptionHandler(value=ProductNotFoundException.class)
	public ResponseEntity<ExceptionResponse> productNotFoundException(ProductNotFoundException exception){
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.NOT_FOUND.name(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=InvalidProductException.class)
	public ResponseEntity<ExceptionResponse>invalidProductException(InvalidProductException exception){
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.BAD_REQUEST.name(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=ProductAlreadyExistsException.class)
	public ResponseEntity<ExceptionResponse>productAlreadyException(ProductAlreadyExistsException exception){
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.BAD_REQUEST.name(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<ExceptionResponse> userNotFoundException(UserNotFoundException exception){
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.NOT_FOUND.name(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=InvalidUserException.class)
	public ResponseEntity<ExceptionResponse>invalidUserException(InvalidUserException exception){
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.BAD_REQUEST.name(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=UserAlreadyExistsException.class)
	public ResponseEntity<ExceptionResponse>userAlreadyException(UserAlreadyExistsException exception){
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.BAD_REQUEST.name(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value=OrderNotFoundException.class)
	public ResponseEntity<ExceptionResponse> orderNotFoundException(OrderNotFoundException exception){
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.NOT_FOUND.name(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=InvalidOrderException.class)
	public ResponseEntity<ExceptionResponse>invalidOrderException(InvalidOrderException exception){
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.BAD_REQUEST.name(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=OrderAlreadyExistsException.class)
	public ResponseEntity<ExceptionResponse>orderAlreadyException(OrderAlreadyExistsException exception){
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.BAD_REQUEST.name(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value=InternalServerErrorException.class)
	public ResponseEntity<ExceptionResponse>internalServerErrorException(InternalServerErrorException exception){
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.INTERNAL_SERVER_ERROR.name(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
