package com.eu.food.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {
	
	private Utils() {}
	
	public static ResponseEntity<String> makeStatus(String message, HttpStatus status) {
		return new ResponseEntity<>(message, status);
	}

}