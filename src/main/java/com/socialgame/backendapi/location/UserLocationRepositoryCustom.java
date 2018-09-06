package com.socialgame.backendapi.location;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserLocationRepositoryCustom {

	List<UserLocation> getClosest(UserLocation userLocation);
	

}
