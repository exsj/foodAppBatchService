package com.eu.food.service;

import org.springframework.http.ResponseEntity;

public interface BatchService {
	
	ResponseEntity<String> handleProduct(String source);
	ResponseEntity<String> handleVendor(String source);

}
