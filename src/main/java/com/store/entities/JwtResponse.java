package com.store.entities;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jwttoken;

	private String message;

	private List<String> roles;

	/**
	 * @return the jwttoken
	 */
	public String getJwttoken() {
		return jwttoken;
	}

	/**
	 * @param jwttoken the jwttoken to set
	 */
	public void setJwttoken(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
