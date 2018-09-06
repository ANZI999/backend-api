package com.socialgame.backendapi.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.socialgame.backendapi.ErrorResponse;
import com.socialgame.backendapi.Response;

@RequestMapping(value="/user")
@RestController
public class UserController {
	
	@Autowired
	private MongoOperations operations;	
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public Response signup(@Valid @RequestBody User user, BindingResult validUser) {
		if (validUser.hasErrors()) {
            return new ErrorResponse(validUser.getAllErrors());
        }
		
		operations.save(user);
		return new Response();
	}
}
