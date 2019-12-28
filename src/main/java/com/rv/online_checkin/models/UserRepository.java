package com.rv.online_checkin.models;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

	Optional<User> findByUserName(String userName);	
}
