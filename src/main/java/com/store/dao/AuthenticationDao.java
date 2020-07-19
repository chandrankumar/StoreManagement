package com.store.dao;

import org.springframework.security.core.userdetails.UserDetails;

import com.store.entities.Users;

public interface AuthenticationDao{

	public UserDetails findByUsername(String username);
	
	public String registerUser(Users user);
}
