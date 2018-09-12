package com.socialgame.backendapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialgame.backendapi.model.UserLocation;
import com.socialgame.backendapi.repository.UserLocationRepository;
import com.socialgame.backendapi.response.Response;
import com.socialgame.backendapi.response.SuccessResponse;

@RequestMapping(value="/location")
@RestController
public class UserLocationController {
	
	@Autowired
	private UserLocationRepository userLocationRepository;
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public Response update(@RequestBody UserLocation location) {
		userLocationRepository.save(location);
		return new SuccessResponse(null);
	}
	
	@RequestMapping(value="/closest/{userID}", method = RequestMethod.GET)
	public Response closest(@PathVariable String userID) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<UserLocation> closest = userLocationRepository.getClosest(userID);
		
		String closestJSON = mapper.writeValueAsString(closest);
		return new SuccessResponse(closestJSON);
	}
}
