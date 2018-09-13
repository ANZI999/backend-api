package com.socialgame.backendapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.socialgame.backendapi.model.ExtendedUserDetails;
import com.socialgame.backendapi.model.User;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userData = repository.findByUsername(username);
		
		if(userData == null) {
			throw new UsernameNotFoundException("User not found");
	    }
		
	    return new ExtendedUserDetails(userData);
	}
}
