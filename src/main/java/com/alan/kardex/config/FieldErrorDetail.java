package com.alan.kardex.config;

public class FieldErrorDetail {
	private String field;
	private String detail;
	
	public FieldErrorDetail(String field, String detail) {
		super();
		this.field = field;
		this.detail = detail;
	}
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getDetails() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "FiledErrorDetail [field=" + field + ", detail=" + detail + "]";
	}
}