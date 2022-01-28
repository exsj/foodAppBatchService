package com.eu.food.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eu.food.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByVendorIdAndVendorProductId(Long vendorId, Long vendorProductId);

}
