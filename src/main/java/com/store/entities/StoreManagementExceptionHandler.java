package com.store.entities;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StoreManagementExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(StoreManagementException.class)
	public final ResponseEntity<ExceptionResponse> handleException(StoreManagementException se) {

		ExceptionResponse re = new ExceptionResponse(se.fillInStackTrace().getMessage(),
				String.valueOf(se.getStatus()));
		return new ResponseEntity<ExceptionResponse>(re, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

}
