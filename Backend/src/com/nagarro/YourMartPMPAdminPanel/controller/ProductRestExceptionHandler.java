package com.nagarro.YourMartPMPAdminPanel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nagarro.YourMartPMPAdminPanel.customException.ProductNotFoundException;
import com.nagarro.YourMartPMPAdminPanel.entity.ProductErrorResponse;

@ControllerAdvice
public class ProductRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException exc){
		ProductErrorResponse error = new ProductErrorResponse(HttpStatus.NOT_FOUND.value(),exc.getMessage(),System.currentTimeMillis());
	   return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ProductErrorResponse> handleException(Exception exc){
		ProductErrorResponse error = new ProductErrorResponse(HttpStatus.BAD_REQUEST.value(),exc.getMessage(),System.currentTimeMillis());
	   return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
}
