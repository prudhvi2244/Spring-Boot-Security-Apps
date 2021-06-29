package com.raj.in.service;

import java.util.Optional;

import com.raj.in.model.User;

public interface IUserService {
	
	public Integer saveUser(User user);
	Optional<User> findByUsername(String username);

}
