package com.socialgame.backendapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.socialgame.backendapi.user.User;

public interface UserRepository extends MongoRepository<User, String>  {
	User findByUsername(String username);
}
