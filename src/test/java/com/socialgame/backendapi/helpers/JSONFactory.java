package com.socialgame.backendapi.helpers;

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
	
	public static String generateLocation(double longitude, double latitude) throws JSONException {
		String json = new JSONObject()
                .put("longitude", longitude)
                .put("latitude", latitude)
                .toString();
		return json;
	}
}
