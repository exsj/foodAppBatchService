package com.eu.food.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Vendor {

	@Id
	private Long vendorId;
	
	private String name;
	
	private String description;
	
}
