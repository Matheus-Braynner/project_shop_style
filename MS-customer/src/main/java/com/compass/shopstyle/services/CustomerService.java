package com.compass.shopstyle.services;

import com.compass.shopstyle.dto.ChangePasswordDTO;
import com.compass.shopstyle.dto.CustomerDTO;
import com.compass.shopstyle.dto.CustomerFormDTO;
import com.compass.shopstyle.dto.CustomerLoginFormDTO;
import com.compass.shopstyle.dto.CustomerNewFormDTO;
import com.compass.shopstyle.entities.Customer;

public interface CustomerService {
	
	CustomerDTO insert(CustomerFormDTO customerObj);
	
	CustomerDTO findById(Long id);
	
	CustomerDTO update(Long id, CustomerNewFormDTO customerObj);
	
	CustomerDTO login(CustomerLoginFormDTO customerLoginObj);
	
	Customer findByEmail(String email);
	
	CustomerDTO changePassword(Long id, ChangePasswordDTO changePasswordObj);
}
