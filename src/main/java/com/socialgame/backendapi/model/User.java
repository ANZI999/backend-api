package com.socialgame.backendapi.model;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Document(collection = "users")
public class User {
	public static final String  USERNAME_VALIDATION_ERROR = "Username length must be between 4 and 15 characters.";
	public static final String  PASSWORD_VALIDATION_ERROR = "Password must be at least 6 characters long.";
	
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	@Size(min=4, max=15, message=USERNAME_VALIDATION_ERROR)
	private String username;
	
	@Size(min=6, message=PASSWORD_VALIDATION_ERROR)
	private String password;
	
	public String getID() {
		return id;
	}
	
	public void setID(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password.length() < 6) {
			this.password = "xx";
		} else {
			this.password = new BCryptPasswordEncoder().encode(password);
		}
	}
	
	
}
