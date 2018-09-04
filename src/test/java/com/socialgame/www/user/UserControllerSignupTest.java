package com.socialgame.www.user;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
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

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerSignupTest {
	private static final String USERNAME = "andres";
	private static final String PASSWORD = "xxx";
	
	@MockBean
	private MongoOperations operations;
	
	@Autowired
	private MockMvc mockMvc;
	 
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void happyPath() throws Exception {
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		User user = new User();
		user.setPassword(PASSWORD);
		user.setUsername(USERNAME);
		mockMvc.perform(post("/user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user)))
				.andExpect(jsonPath("code", is(HttpStatus.OK.value())));
		verify(operations).save(captor.capture());
		assertEquals(PASSWORD, captor.getValue().getPassword());
		assertEquals(USERNAME, captor.getValue().getUsername());
	}
}
