package com.socialgame.backendapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialgame.backendapi.Session;
import com.socialgame.backendapi.model.UserLocation;
import com.socialgame.backendapi.repository.UserLocationRepository;
import com.socialgame.backendapi.response.Response;
import com.socialgame.backendapi.response.SuccessResponse;

@RequestMapping(value="/location")
@RestController
public class UserLocationController {
	
	@Autowired
	private Session session;
	
	@Autowired
	private UserLocationRepository userLocationRepository;
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public Response update(@RequestBody UserLocation userLocation) {
		userLocation.setUserID(session.getUser().getID());
		userLocationRepository.save(userLocation);
		return new SuccessResponse(null);
	}
	
	@RequestMapping(value="/closest", method = RequestMethod.GET)
	public Response closest() throws JsonProcessingException {
		String userID = session.getUser().getID();
		List<UserLocation> closest = userLocationRepository.getClosest(userID);
		
		ObjectMapper mapper = new ObjectMapper();
		String closestJSON = mapper.writeValueAsString(closest);
		return new SuccessResponse(closestJSON);
	}
}
