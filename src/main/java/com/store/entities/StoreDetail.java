package com.store.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "storeDtl", schema = "store")
public class StoreDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	@SequenceGenerator(schema = "veggi", name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
	@Column(name = "store_id")
	private Integer id;

	@Column(name = "store_name")
	private String storename;

	@Column(name = "address")
	private String address;

	@Column(name = "GSTIN")
	private String GSTIN;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email")
	private String email;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the storename
	 */
	public String getStorename() {
		return storename;
	}

	/**
	 * @param storename the storename to set
	 */
	public void setStorename(String storename) {
		this.storename = storename;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the gSTIN
	 */
	public String getGSTIN() {
		return GSTIN;
	}

	/**
	 * @param gSTIN the gSTIN to set
	 */
	public void setGSTIN(String gSTIN) {
		GSTIN = gSTIN;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
