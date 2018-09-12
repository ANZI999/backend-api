package com.socialgame.backendapi;

import org.springframework.http.HttpStatus;

public class ErrorResponse extends Response {
	
	public ErrorResponse(String errors) {
		super(HttpStatus.BAD_REQUEST.value(), "error", errors, null);
	}
	
}
