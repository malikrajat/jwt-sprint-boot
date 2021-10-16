package com.jwt.model;

public class JwtResponse {
	String token;

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + "]";
	}

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
