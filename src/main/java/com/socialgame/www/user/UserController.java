package com.socialgame.www.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.socialgame.www.Response;

@RequestMapping(value="/user")
@RestController
public class UserController {
	
	@Autowired
	private MongoOperations operations;	
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public Response signup(@RequestBody User user) {
		operations.save(user);
		return new Response();
	}
}
