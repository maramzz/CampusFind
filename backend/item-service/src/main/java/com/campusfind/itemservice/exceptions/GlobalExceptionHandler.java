package com.campusfind.itemservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> gererValidationException(
	        MethodArgumentNotValidException ex) {

	    String message = ex.getBindingResult()
	            .getFieldErrors()
	            .get(0)
	            .getDefaultMessage();

	    return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body(message);
	}
} 