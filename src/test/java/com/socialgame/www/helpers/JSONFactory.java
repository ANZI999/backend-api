package com.socialgame.www.helpers;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONFactory {
	public static String generateUser(String username, String password) throws JSONException {
		String json = new JSONObject()
                .put("password", password)
                .put("username", username)
                .toString();
		return json;
	}
}
