package com.eu.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.eu.food.service.BatchService;
 
@RestController
public class JobInvokerController {
 
	@Autowired
	BatchService batchService;
	
    @GetMapping("/run/vendor/{source}")
    public ResponseEntity<String> handleVendor(@PathVariable("source") String source) throws Exception {
    	return batchService.handleVendor(source);
    }
    
    @GetMapping("/run/product/{source}")
    public ResponseEntity<String> handleProduct(@PathVariable("source") String source) throws Exception {
    	return batchService.handleProduct(source);
    }
}