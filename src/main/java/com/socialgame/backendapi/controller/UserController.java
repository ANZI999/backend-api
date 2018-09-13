package com.socialgame.backendapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.socialgame.backendapi.model.User;
import com.socialgame.backendapi.repository.UserRepository;
import com.socialgame.backendapi.response.ErrorResponse;
import com.socialgame.backendapi.response.Response;
import com.socialgame.backendapi.response.SuccessResponse;
import com.socialgame.backendapi.utils.Helpers;

@RequestMapping(value="/user")
@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;	
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public Response signup(@Valid @RequestBody User user, BindingResult validUser) {
		if (validUser.hasErrors()) {
            return new ErrorResponse(Helpers.parseErrors(validUser.getAllErrors()));
        }
		
		userRepository.save(user);
		return new SuccessResponse(null);
	}
}
