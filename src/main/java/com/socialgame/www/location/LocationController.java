package com.socialgame.www.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.socialgame.www.Response;

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
}
