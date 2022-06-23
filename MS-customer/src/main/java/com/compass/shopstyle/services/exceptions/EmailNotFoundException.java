package com.compass.shopstyle.services.exceptions;

public class EmailNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmailNotFoundException(Object email) {
		super("Email not found = " + email);
	}
}
