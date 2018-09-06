package com.socialgame.backendapi;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class Response {
	
	protected int code;
	protected String status;
	
	@JsonRawValue
	protected String message;
	
	@JsonRawValue
	protected String data;
	
	public Response() {
		code = 200;
		status = "success";
	}

	public Response(String dataJSON) {
		code = 200;
		status = "success";
		data = dataJSON;
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