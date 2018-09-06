package com.socialgame.www.location;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialgame.www.helpers.JSONFactory;
import com.socialgame.www.user.User;
import com.socialgame.www.user.UserController;

@RunWith(SpringRunner.class)
@WebMvcTest(LocationController.class)
public class LocationControllerUpdateTest {
	
	@MockBean
	private UserLocationRepository userLocationRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void happyPath() throws Exception {
		ArgumentCaptor<UserLocation> captor = ArgumentCaptor.forClass(UserLocation.class);
		
		String userLocation = JSONFactory.generateUserLocation("karl", 12.5654654, 67.434225);
		mockMvc.perform(post("/location/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userLocation))
				.andExpect(jsonPath("code", is(HttpStatus.OK.value())));
		verify(userLocationRepository).save(captor.capture());
		assertEquals("karl", captor.getValue().getUserID());
	}
}
