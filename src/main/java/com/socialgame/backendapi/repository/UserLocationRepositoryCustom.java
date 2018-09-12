package com.socialgame.backendapi.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.socialgame.backendapi.location.UserLocation;

public interface UserLocationRepositoryCustom {

	List<UserLocation> getClosest(String userID);
	

}
