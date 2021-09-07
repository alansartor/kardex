package com.alan.kardex.config;

import org.springframework.http.HttpStatus;

public abstract class AbstractExeption extends RuntimeException implements FinalUserException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3990733445853935837L;

	private String title;
	private HttpStatus status;
	private String detail;
	
	public AbstractExeption(String title, HttpStatus status, String detail) {
		super(title);
		this.title = title;
		this.status = status;
		this.detail = detail;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}	
}