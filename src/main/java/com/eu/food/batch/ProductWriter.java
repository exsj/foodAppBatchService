package com.eu.food.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eu.food.entity.Product;
import com.eu.food.repository.ProductRepository;

@Component
public class ProductWriter implements ItemWriter<Product>{
	
	@Autowired
	private ProductRepository repo;

	@Override
	@Transactional
	public void write(List<? extends Product> products) throws Exception {
		repo.saveAll(products);
	}
	
}