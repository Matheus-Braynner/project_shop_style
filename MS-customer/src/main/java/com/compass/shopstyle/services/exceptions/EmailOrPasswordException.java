package com.compass.shopstyle.services.exceptions;

public class EmailOrPasswordException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EmailOrPasswordException(String msg) {
		super(msg);
	}

}
