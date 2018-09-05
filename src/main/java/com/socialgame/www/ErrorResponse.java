package com.socialgame.www;

public class ErrorResponse extends Response {
	
	public ErrorResponse(String message) {
		this.code = 400;
		this.status = "error";
		this.message = message;
	}
	
}
