package com.compass.payment.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.compass.payment.services.exceptions.DatabaseException;
import com.compass.payment.services.exceptions.FailToConvertToObject;
import com.compass.payment.services.exceptions.NotValidPaymentException;
import com.compass.payment.services.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
			String error = "Object not found";
			HttpStatus status = HttpStatus.NOT_FOUND;
			StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> resourceNotFound(DatabaseException e, HttpServletRequest request) {
			String error = "Database exception";
			HttpStatus status = HttpStatus.NOT_FOUND;
			StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(NotValidPaymentException.class)
	public ResponseEntity<StandardError> resourceNotFound(NotValidPaymentException e, HttpServletRequest request) {
			String error = "Database exception";
			HttpStatus status = HttpStatus.NOT_FOUND;
			StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(FailToConvertToObject.class)
	public ResponseEntity<StandardError> resourceNotFound(FailToConvertToObject e, HttpServletRequest request) {
			String error = "Fail to convert to object";
			HttpStatus status = HttpStatus.BAD_REQUEST;
			StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(status).body(err);
	}
}


