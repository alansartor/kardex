package com.alan.kardex.config;

public class FieldErrorDetailExeption extends RuntimeException implements FinalUserException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1809502723694729802L;
	
	private final FieldErrorDetail detail;

	public FieldErrorDetailExeption(FieldErrorDetail detail) {
		super(detail.toString());
		this.detail = detail;
	}

	public FieldErrorDetail getDetail() {
		return detail;
	}
}
