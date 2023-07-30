package com.sbms.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<String> seatsNotAvailableException(SeatsNotAvailableException e) {
		String message = e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.EXPECTATION_FAILED);
	}
	

}
