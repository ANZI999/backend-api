package com.socialgame.www.location;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserLocationRepositoryIntegrationTest {
	
	@Autowired
	private UserLocationRepository userLocationRepository;

	@Before
	public void init() {
		userLocationRepository.deleteAll();
		
		UserLocation userLocationOne = new UserLocation("karl", 46.123, 12.0); //75
		userLocationRepository.save(userLocationOne);
		
		UserLocation userLocationTwo = new UserLocation("mihkel", 45.910, 12.9); //28
		userLocationRepository.save(userLocationTwo);
		
		UserLocation userLocationThree = new UserLocation("artur", 45.1233, 12.5); //95
		userLocationRepository.save(userLocationThree);
	}
	
    @Test
    public void getClosest() throws Exception {
    	UserLocation userLocationFour = new UserLocation("rita", 45.985, 12.658);
    	List<UserLocation> closest = userLocationRepository.getClosest(userLocationFour);
    	assertEquals(3, closest.size());
    	assertEquals("mihkel", closest.get(0).getUserID());
    	assertEquals("karl", closest.get(1).getUserID());
    }
    
    @Test
    public void getClosestExcludeMyself() throws Exception {
    	UserLocation userLocationFour = new UserLocation("mihkel", 45.985, 12.658);
    	List<UserLocation> closest = userLocationRepository.getClosest(userLocationFour);
    	assertEquals(2, closest.size());
    }
}
