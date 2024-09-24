package com.book.CodingChallenge.exception;

public class InvalidCredentialsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InvalidCredentialsException(String message) {
		super();
		this.message = message;
	}
	
	

}
