package com.socialgame.www;

public class Response {
	
	private int code;
	private String status;
	private String message;
	private String data;
	
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
