package com.store.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.store.entities.Users;

public interface AuthenticationService {

	UserDetails loadUserByUsername(String username);

	String registerUser(Users user);

}
