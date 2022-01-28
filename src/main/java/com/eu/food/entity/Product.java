package com.eu.food.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	private Long vendorId;
	
	private Long vendorProductId;
	
	private String name;
	
	private String description;
	
	private int price;
	
	

}
