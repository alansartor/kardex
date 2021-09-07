package com.alan.kardex.config;

import org.springframework.http.HttpStatus;

public class InternalServerErrorExeption extends AbstractExeption{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3550899320600663138L;
	
	public InternalServerErrorExeption(String title, String detail) {
		super(title, HttpStatus.INTERNAL_SERVER_ERROR, detail);
	}
}
