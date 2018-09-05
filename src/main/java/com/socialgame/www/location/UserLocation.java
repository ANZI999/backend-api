package com.socialgame.www.location;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_LOCATION")
public class UserLocation {
	
	@Id
	private String userID;
	private double longitude;
	private double latitude;

	public UserLocation() {}
	
	public UserLocation(String userID, double longitude, double latitude) {
		this.userID = userID;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getUserID() {
		return userID;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	
	
}
