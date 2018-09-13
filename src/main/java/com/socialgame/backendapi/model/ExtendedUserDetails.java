package com.socialgame.backendapi.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class ExtendedUserDetails extends org.springframework.security.core.userdetails.User {
	
	private static final List<SimpleGrantedAuthority> AUTHORITIES = Arrays.asList(new SimpleGrantedAuthority("user"));
	
	private User user;

	public ExtendedUserDetails(User user) {
		super(user.getUsername(), user.getPassword(), AUTHORITIES);	
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

}
