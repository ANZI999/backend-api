package com.socialgame.backendapi.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialgame.backendapi.Response;

@RequestMapping(value="/location")
@RestController
public class LocationController {
	
	@Autowired
	private UserLocationRepository userLocationRepository;
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public Response update(@RequestBody UserLocation location) {
		userLocationRepository.save(location);
		return new Response();
	}
	
	@RequestMapping(value="/closest/{userID}", method = RequestMethod.GET)
	public Response closest(@PathVariable String userID) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<UserLocation> closest = userLocationRepository.getClosest(userID);
		
		String closestJSON = mapper.writeValueAsString(closest);
		return new Response(closestJSON);
	}
}