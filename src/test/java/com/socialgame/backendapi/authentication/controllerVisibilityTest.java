package com.socialgame.backendapi.authentication;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.socialgame.backendapi.Session;
import com.socialgame.backendapi.config.WebSecurityConfig;
import com.socialgame.backendapi.helpers.JSONFactory;
import com.socialgame.backendapi.model.User;
import com.socialgame.backendapi.repository.UserLocationRepository;
import com.socialgame.backendapi.repository.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(secure = true)
@Import(WebSecurityConfig.class)
public class controllerVisibilityTest {
	
	private static final String USERNAME = "karl";
	private static final String PASSWORD = "xxxxxx";
	
	@MockBean
	private UserLocationRepository userLocationRepository;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private Session session;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void userSignUp() throws Exception {		
		String userJSON = JSONFactory.generateUser(USERNAME, PASSWORD);
		mockMvc.perform(post("/user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJSON))
			.andExpect(status().isOk());
	}
	
	@Test
	public void locationGetClosest() throws Exception {		
		User user = new User();
		user.setPassword(PASSWORD);		
		user.setUsername(USERNAME);
		when(userRepository.findByUsername(USERNAME)).thenReturn(user);
		when(session.getUser()).thenReturn(user);
		
		mockMvc.perform(get("/location/closest")
				.with(httpBasic(USERNAME, PASSWORD)))
			.andExpect(status().isOk());
	}
	
	@Test
	public void locationGetClosestWithOutPassword() throws Exception {				
		mockMvc.perform(get("/location/closest"))
			.andExpect(status().isUnauthorized());
	}
}
