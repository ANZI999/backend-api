package com.socialgame.backendapi.user;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
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

import com.socialgame.backendapi.helpers.JSONFactory;
import com.socialgame.backendapi.repository.UserRepository;
import com.socialgame.backendapi.user.User;
import com.socialgame.backendapi.user.UserController;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class, secure = false)
public class UserControllerSignupTest {
	private static final String USERNAME = "andres";
	private static final String PASSWORD = "xxxxxx";
	
	private static final String SHORT_USERNAME = "and";
	private static final String LONG_USERNAME = "veryveryveryvery";
	
	private static final String SHORT_PASSWORD = "xxxxx";
	
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void happyPath() throws Exception {		
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		String userJSON = JSONFactory.generateUser(USERNAME, PASSWORD);
		mockMvc.perform(post("/user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJSON))
				.andExpect(jsonPath("code", is(HttpStatus.OK.value())));
		verify(userRepository).save(captor.capture());
		assertThat(captor.getValue().getPassword(), not(equalTo(PASSWORD)));
		assertEquals(USERNAME, captor.getValue().getUsername());
	}
	
	@Test
	public void tooShortUsername() throws Exception {
		String userJSON = JSONFactory.generateUser(SHORT_USERNAME, PASSWORD);
		mockMvc.perform(post("/user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJSON))
				.andExpect(jsonPath("code", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("$['message']['username']", is(User.USERNAME_VALIDATION_ERROR)));
	}
	
	@Test
	public void tooLongUsername() throws Exception {
		String userJSON = JSONFactory.generateUser(LONG_USERNAME, PASSWORD);
		mockMvc.perform(post("/user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJSON))
				.andExpect(jsonPath("code", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("$['message']['username']", is(User.USERNAME_VALIDATION_ERROR)));
	}
	
	@Test
	public void tooShortPassword() throws Exception {
		String userJSON = JSONFactory.generateUser(USERNAME, SHORT_PASSWORD);
		mockMvc.perform(post("/user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJSON))
				.andExpect(jsonPath("code", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("$['message']['password']", is(User.PASSWORD_VALIDATION_ERROR)));
	}
	
	@Test
	public void shortPasswordAndUsername() throws Exception {
		String userJSON = JSONFactory.generateUser(SHORT_USERNAME, SHORT_PASSWORD);
		mockMvc.perform(post("/user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJSON))
				.andExpect(jsonPath("code", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("$['message']['password']", is(User.PASSWORD_VALIDATION_ERROR)))
				.andExpect(jsonPath("$['message']['username']", is(User.USERNAME_VALIDATION_ERROR)));
	}
}
