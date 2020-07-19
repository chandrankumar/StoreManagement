package com.store.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.store.entities.Users;

@Repository
public class AuthenticationDaoImpl implements AuthenticationDao{
	
	@Autowired
	EntityManager manager;

	@Override
	@Transactional
	public UserDetails findByUsername(String username) {

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Users> criteriaQuery = builder.createQuery(Users.class);
		Root<Users> root = criteriaQuery.from(Users.class);
		criteriaQuery.select(root);		
		criteriaQuery.where(builder.equal(root.get("username"), username));
		TypedQuery<Users> query = manager.createQuery(criteriaQuery);
		Users result  = query.getSingleResult();
		
		result.getUsersRole().stream().forEach( role ->{
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		});
		
		System.out.print("usr: "+ result.getUsername()+" pwd: "+" role: "+grantedAuthorities.toString());
		return new User(result.getUsername(), result.getPassword(), grantedAuthorities);
	}

	@Override
	@Transactional
	public String registerUser(Users user) {
		manager.persist(user);
		manager.flush();
		if(user.getId() != null){
			return "Created Successfully";
		}else {
			return "Not Created";
		}
	}


}
