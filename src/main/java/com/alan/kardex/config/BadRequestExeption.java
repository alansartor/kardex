package com.alan.kardex.config;

import org.springframework.http.HttpStatus;

public class BadRequestExeption extends AbstractExeption{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3550899320600663138L;
	
	public BadRequestExeption(String title, String detail) {
		super(title, HttpStatus.BAD_REQUEST, detail);
	}
}
