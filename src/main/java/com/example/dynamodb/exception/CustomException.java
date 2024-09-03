package com.example.dynamodb.exception;

public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	//private String message;

	public CustomException() {
		super();
	}

	public CustomException(String customMessage) {
		super(customMessage);
		//this.message = customMessage;
	}
}
