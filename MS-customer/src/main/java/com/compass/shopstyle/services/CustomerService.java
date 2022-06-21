package com.compass.shopstyle.services;

import com.compass.shopstyle.dto.CustomerDTO;
import com.compass.shopstyle.dto.CustomerFormDTO;
import com.compass.shopstyle.dto.CustomerNewFormDTO;

public interface CustomerService {
	
	CustomerDTO insert(CustomerFormDTO customerObj);
	
	CustomerDTO findById(Long id);
	
	CustomerDTO update(Long id, CustomerNewFormDTO customerObj);
}
