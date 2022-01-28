package com.eu.food.batch;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eu.food.entity.Vendor;
import com.eu.food.repository.VendorRepository;

@Component
public class VendorProcessor implements ItemProcessor<Vendor, Vendor> {

	@Autowired
	VendorRepository vendorRepository;
	
	@Override
	public Vendor process(Vendor vendor) throws Exception {
		Optional<Vendor> vendorDb = vendorRepository.findById(vendor.getVendorId());
		if (vendorDb.isPresent()) {
			vendorDb.get().setName(vendor.getName());
			vendorDb.get().setDescription(vendor.getDescription());
		} else {
			return vendor;
		}
		
		return vendorDb.get();
	}

}

