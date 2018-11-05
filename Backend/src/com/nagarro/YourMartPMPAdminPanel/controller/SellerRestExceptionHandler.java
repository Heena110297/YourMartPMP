package com.nagarro.YourMartPMPAdminPanel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nagarro.YourMartPMPAdminPanel.customException.SellerNotFoundException;
import com.nagarro.YourMartPMPAdminPanel.entity.SellerErrorResponse;

@ControllerAdvice
public class SellerRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<SellerErrorResponse> handleException(SellerNotFoundException exc) {
		SellerErrorResponse error = new SellerErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<SellerErrorResponse> handleException(Exception exc) {
		SellerErrorResponse error = new SellerErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
