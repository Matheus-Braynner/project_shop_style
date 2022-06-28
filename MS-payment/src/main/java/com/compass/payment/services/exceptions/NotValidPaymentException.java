package com.compass.payment.services.exceptions;

public class NotValidPaymentException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NotValidPaymentException(String msg) {
		super(msg);
	}

}
