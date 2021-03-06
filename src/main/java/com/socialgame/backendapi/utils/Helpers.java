package com.socialgame.backendapi.utils;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class Helpers {
	public static String parseErrors(List<ObjectError> errors) {
		JSONObject json = new JSONObject();
		try {
			for(int i = 0; i < errors.size(); i++) {
				FieldError error = (FieldError) errors.get(i);
				json.put(error.getField(), error.getDefaultMessage());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
}
