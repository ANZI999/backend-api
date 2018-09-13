package com.socialgame.backendapi;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.socialgame.backendapi.model.ExtendedUserDetails;
import com.socialgame.backendapi.model.User;

@Service
public class Session {
	public User getUser() {
		ExtendedUserDetails userDetails = (ExtendedUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userDetails.getUser();
	}
}
