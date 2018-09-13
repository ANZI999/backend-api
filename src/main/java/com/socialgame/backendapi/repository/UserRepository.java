package com.socialgame.backendapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.socialgame.backendapi.model.User;

public interface UserRepository extends MongoRepository<User, String>  {
	User findByUsername(String username);
}
