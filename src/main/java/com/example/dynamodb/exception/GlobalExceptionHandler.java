package com.example.dynamodb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = OrderAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ErrorResponse handleOrderAlreadyExistsException(OrderAlreadyExistException ex, HttpServletRequest request) {
		log.info("Inside handleOrderAlreadyExistsException method....");
		//String test = request.getRequestURI();
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), request.getRequestURI());
    }

	@ExceptionHandler(value = OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleOrderNotExistsException(OrderNotFoundException ex) {
		log.info("Inside handleOrderNotExistsException method....");
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }


	/*@ExceptionHandler(Exception.class)
	//@ResponseStatus(HttpStatus.BAD_REQUEST)
	//@ResponseBody
    public ResponseEntity<Object> handleExceptionInternal(Exception ex, 
                HttpServletRequest request, HttpServletResponse response) {
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }*/

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
    public ErrorResponse handleAllExceptions(Exception ex, 
			HttpServletRequest request, HttpServletResponse response) {
		// Log the exception (optional)
		log.info("Inside handleAllExceptions method....");
        ex.printStackTrace();
        return new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage(), request.getRequestURI());
    }
	
	/*@ExceptionHandler(Exception.class)
	//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
    public ErrorResponse handleAllExceptions(Exception ex, 
                HttpServletRequest request, HttpServletResponse response, HttpStatus status) {
		// Log the exception (optional)
		System.out.println("Status Code2:- " + status.value());
        ex.printStackTrace();
     // Return a custom error response
        //return new ResponseEntity<>("An internal server error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ErrorResponse(status.value(), ex.getMessage(), request.getRequestURI());
    }*/
	
	
	/*@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorResponse response = new ErrorResponse();
	    response.setMessage("Internal error occured, " + ex.getStackTrace()[0]);
	    System.out.println("big exceptions");

	    return new ResponseEntity<ErrorResponse>(response, headers, status);
	}*/
	
	@ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
		log.info("Inside handleNullPointerException method....");
        // Log the exception (optional)
        ex.printStackTrace();
        // Return a custom error response
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
