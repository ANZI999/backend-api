package com.socialgame.backendapi.location;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LocationController.class, secure = false)
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
	
		when(userLocationRepository.getClosest(any(String.class))).thenReturn(closestResult);
		
		mockMvc.perform(get("/location/closest/karl"))
				.andExpect(jsonPath("code", is(HttpStatus.OK.value())))
				.andExpect(jsonPath("$['data']", hasSize(3)))
				.andExpect(jsonPath("$['data'][0]['userID']", is("A")))
				.andExpect(jsonPath("$['data'][1]['userID']", is("B")))
				.andExpect(jsonPath("$['data'][2]['userID']", is("C")));
		
	}
}
