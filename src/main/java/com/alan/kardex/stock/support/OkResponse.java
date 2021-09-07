package com.alan.kardex.stock.support;

import org.springframework.http.HttpStatus;

public class OkResponse {
	private String title = "Success operation";
	private int status = HttpStatus.OK.value();
	private String detail;
	
	public OkResponse(String detail) {
		super();
		this.detail = detail;
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
	
	
}
