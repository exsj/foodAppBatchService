package com.eu.food.batch;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eu.food.entity.Product;
import com.eu.food.repository.ProductRepository;

@Component
public class ProductProcessor implements ItemProcessor<Product, Product> {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product process(Product product) throws Exception {
		Optional<Product> productDb = productRepository.findByVendorIdAndVendorProductId(product.getVendorId(), product.getVendorProductId());
		if (productDb.isPresent()) {
			product.setProductId(productDb.get().getProductId());
		}
	
		return product;
		
	}

}
