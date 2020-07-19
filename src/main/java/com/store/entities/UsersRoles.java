package com.store.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users_roles", schema = "store")
public class UsersRoles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_ROLE_SEQ")
	@SequenceGenerator(schema="veggi",name="USER_ROLE_SEQ",sequenceName="USER_ROLE_SEQ",allocationSize=1)
	@Column(name="user_role_id")
	private int userRoleId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	@Column(name = "authority")
	private String role;

	/**
	 * @return the userRoleId
	 */
	public int getUserRoleId() {
		return userRoleId;
	}

	/**
	 * @param userRoleId the userRoleId to set
	 */
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**
	 * @return the users
	 */
	public Users getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Users users) {
		this.users = users;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
