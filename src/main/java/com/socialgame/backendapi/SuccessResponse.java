package com.socialgame.backendapi;

import org.springframework.http.HttpStatus;

public class SuccessResponse extends Response {
	
	public SuccessResponse(String dataJSON) {
		super(HttpStatus.OK.value(), "success", null, dataJSON);
	}
}
