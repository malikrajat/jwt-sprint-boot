package com.jwt.model;

public class JwtRequest {
	String userName;
	String password;

	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtRequest [userName=" + userName + ", password=" + password + "]";
	}
}
