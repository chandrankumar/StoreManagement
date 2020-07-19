package com.store.entities;

import org.springframework.http.HttpStatus;

public class StoreManagementException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final HttpStatus status;

	/**
	 * @param message
	 * @param status
	 */
	public StoreManagementException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return this.status;
	}
}
