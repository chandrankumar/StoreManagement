package com.store.entities;

public class ExceptionResponse {

	private String message;
	private String statuscode;
	
	public ExceptionResponse(String message, String statuscode) {
		super();
		this.message = message;
		this.statuscode = statuscode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the statuscode
	 */
	public String getStatuscode() {
		return statuscode;
	}

	
	
}
