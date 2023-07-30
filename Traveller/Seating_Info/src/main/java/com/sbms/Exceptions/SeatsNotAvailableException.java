package com.sbms.Exceptions;

public class SeatsNotAvailableException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SeatsNotAvailableException(String message){
		super(message);
	}

}
