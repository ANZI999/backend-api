package com.socialgame.backendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialgame.backendapi.location.UserLocation;

public interface UserLocationRepository extends JpaRepository<UserLocation, String>, UserLocationRepositoryCustom {

}
