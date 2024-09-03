package com.example.dynamodb.exception;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = -3181243389462056634L;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp; //"yyyy-MM-dd'T'HH:mm:ss.S'Z'"
	private int statusCode;
	private String error;
	private String message;
	private String path;
	private List<FieldError> subErrors;

	public ErrorResponse(String message) {
		super();
		this.message = message;
	}

	public ErrorResponse(int statusCode, String message) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

	public ErrorResponse(int statusCode, String message, String path) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.path = path;
		this.timestamp = LocalDateTime.now();
	}
}
