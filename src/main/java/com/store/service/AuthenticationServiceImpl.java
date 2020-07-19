package com.store.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.store.dao.AuthenticationDao;
import com.store.entities.Users;
import com.store.entities.UsersRoles;

@Service
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService{

	@Autowired
	AuthenticationDao authDao;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		
		return authDao.findByUsername(username);
	}

	@Override
	@Transactional
	public String registerUser(Users user) {
		UsersRoles usersRole = new UsersRoles();
		usersRole.setRole("ROLE_USER");
		usersRole.setUsers(user);
		user.setUsersRole(Arrays.asList(usersRole));
		return authDao.registerUser(user);
	}

}
