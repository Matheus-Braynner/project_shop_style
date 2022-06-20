package com.compass.shopstyle.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.compass.shopstyle.services.exceptions.DatabaseException;
import com.compass.shopstyle.services.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
			String error = "Resource not found";
			HttpStatus status = HttpStatus.NOT_FOUND;
			StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request) {
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		String error = "Validation error";
		String message = e.getFieldError().getDefaultMessage();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidationError err = new ValidationError(Instant.now(), status.value(), error, message,
				request.getRequestURI());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err);
	}
	
	
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<StandardError> invalidFormat(InvalidFormatException e, HttpServletRequest request) {
		String error = "invalid format error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	
}
