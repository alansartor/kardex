package com.alan.kardex.config;

import org.springframework.http.HttpStatus;

public class NotFoundExeption extends AbstractExeption{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3550899320600663138L;
	
	public NotFoundExeption(String title, String detail) {
		super(title, HttpStatus.NOT_FOUND, detail);
	}
}
