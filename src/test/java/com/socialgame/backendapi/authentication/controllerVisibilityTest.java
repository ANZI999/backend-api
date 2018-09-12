package com.socialgame.backendapi.authentication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.socialgame.backendapi.config.WebSecurityConfig;
import com.socialgame.backendapi.helpers.JSONFactory;
import com.socialgame.backendapi.location.UserLocation;
import com.socialgame.backendapi.repository.UserLocationRepository;
import com.socialgame.backendapi.repository.UserRepository;
import com.socialgame.backendapi.user.User;
import com.socialgame.backendapi.user.UserController;

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
		
		mockMvc.perform(get("/location/closest/" + USERNAME)
				.with(httpBasic(USERNAME, PASSWORD)))
			.andExpect(status().isOk());
	}
	
	@Test
	public void locationGetClosestWithOutPassword() throws Exception {				
		mockMvc.perform(get("/location/closest/" + USERNAME))
			.andExpect(status().isUnauthorized());
	}
}
