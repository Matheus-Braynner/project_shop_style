package com.compass.shopstyle.services.exceptions;

public class InvalidDataChangePasswordException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidDataChangePasswordException(String msg) {
		super(msg);
	}

}
