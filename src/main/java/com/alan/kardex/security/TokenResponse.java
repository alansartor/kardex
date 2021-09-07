package com.alan.kardex.security;

public class TokenResponse {
	private int status;
	private String token;
	
	public TokenResponse(int status, String token) {
		super();
		this.status = status;
		this.token = token;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
