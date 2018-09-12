package com.socialgame.backendapi.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Response {
	
	private final Logger logger = LoggerFactory.getLogger(Response.class);
	
	protected int code;
	protected String status;
	
	@JsonRawValue
	protected String message;
	
	@JsonRawValue
	protected String data;
	
	public Response(int code, String status, String message, String data) {
		this.code = code;
		this.status = status;
		this.message = message;
		this.data = data;
		
		writeToLog();
	}

	private void writeToLog() {
		try {
			logger.info(new ObjectMapper().writeValueAsString(this));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
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
