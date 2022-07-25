package com.compass.mscatalog.services.exception;

public class FailToConvertToObject extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public FailToConvertToObject(String msg) {
		super(msg);
	}

}
