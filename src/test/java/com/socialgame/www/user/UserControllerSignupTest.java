package com.socialgame.www.user;

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

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerSignupTest {
	private static final String USERNAME = "andres";
	private static final String PASSWORD = "xxxxxx";
	
	private static final String SHORT_USERNAME = "and";
	private static final String LONG_USERNAME = "veryveryveryvery";
	
	private static final String SHORT_PASSWORD = "xxxxx";
	
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
	
	@Test
	public void tooShortUsername() throws Exception {
		User user = new User();
		user.setPassword(PASSWORD);
		user.setUsername(SHORT_USERNAME);
		mockMvc.perform(post("/user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user)))
				.andExpect(jsonPath("code", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("message", is(User.USERNAME_VALIDATION_ERROR)));
	}
	
	@Test
	public void tooLongUsername() throws Exception {
		User user = new User();
		user.setPassword(PASSWORD);
		user.setUsername(LONG_USERNAME);
		mockMvc.perform(post("/user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user)))
				.andExpect(jsonPath("code", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("message", is(User.USERNAME_VALIDATION_ERROR)));
	}
	
	@Test
	public void tooShortPassword() throws Exception {
		User user = new User();
		user.setUsername(USERNAME);
		user.setPassword(SHORT_PASSWORD);		
		mockMvc.perform(post("/user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user)))
				.andExpect(jsonPath("code", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("message", is(User.PASSWORD_VALIDATION_ERROR)));
	}
}
