package com.eu.food.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eu.food.entity.Vendor;
import com.eu.food.repository.VendorRepository;

@Component
public class VendorWriter implements ItemWriter<Vendor>{
	
	@Autowired
	private VendorRepository repo;

	@Override
	@Transactional
	public void write(List<? extends Vendor> vendors) throws Exception {
		repo.saveAll(vendors);
	}
	
}
