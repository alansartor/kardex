package com.alan.kardex.config;

import java.util.List;

public class ExeptionResponse {
	private String title;
	private int status;
	private String detail;
	private String instance;
	
	private List<FieldErrorDetail> errors;
	
	public ExeptionResponse(AbstractExeption ex, String instance) {
		title = ex.getTitle();
		status = ex.getStatus().value();
		detail = ex.getDetail();
		this.instance = instance;
	}

	public ExeptionResponse(String title, int status, String detail, String instance, List<FieldErrorDetail> errors) {
		super();
		this.title = title;
		this.status = status;
		this.detail = detail;
		this.instance = instance;
		this.errors = errors;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getInstance() {
		return instance;
	}
	public void setInstance(String instance) {
		this.instance = instance;
	}
	public List<FieldErrorDetail> getErrors() {
		return errors;
	}
	public void setErrors(List<FieldErrorDetail> errors) {
		this.errors = errors;
	}
}