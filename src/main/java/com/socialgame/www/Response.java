package com.socialgame.www;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class Response {
	
	protected int code;
	protected String status;
	
	@JsonRawValue
	protected String message;
	protected String data;
	
	public Response() {
		code = 200;
		status = "success";
	}

	public int getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getData() {
		return data;
	}
	
	
}
