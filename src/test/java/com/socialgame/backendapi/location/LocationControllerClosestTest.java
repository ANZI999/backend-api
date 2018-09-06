package com.socialgame.backendapi.location;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.socialgame.backendapi.helpers.JSONFactory;
import com.socialgame.backendapi.location.LocationController;
import com.socialgame.backendapi.location.UserLocation;
import com.socialgame.backendapi.location.UserLocationRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(LocationController.class)
public class LocationControllerClosestTest {
	
	@MockBean
	private UserLocationRepository userLocationRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void happyPath() throws Exception {
		List<UserLocation> closestResult = new ArrayList<UserLocation>();
		closestResult.add(new UserLocation("A", 12.5664654, 67.534225));
		closestResult.add(new UserLocation("B", 12.5754654, 67.634225));
		closestResult.add(new UserLocation("C", 12.5854654, 67.934225));
		
		
		String userLocation = JSONFactory.generateUserLocation("karl", 12.5654654, 67.434225);
		when(userLocationRepository.getClosest(any(UserLocation.class))).thenReturn(closestResult);
		
		mockMvc.perform(post("/location/closest")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userLocation))
				.andExpect(jsonPath("code", is(HttpStatus.OK.value())))
				.andExpect(jsonPath("$['data']", hasSize(3)))
				.andExpect(jsonPath("$['data'][0]['userID']", is("A")))
				.andExpect(jsonPath("$['data'][1]['userID']", is("B")))
				.andExpect(jsonPath("$['data'][2]['userID']", is("C")));
		
	}
}
