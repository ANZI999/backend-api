package com.socialgame.backendapi.location;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, String>, UserLocationRepositoryCustom {

}