package com.example.dynamodb.exception;

public class OrderAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public OrderAlreadyExistException() {
		super();
	}

	public OrderAlreadyExistException(String customMessage) {
		super(customMessage);
		this.message = customMessage;
	}
}
