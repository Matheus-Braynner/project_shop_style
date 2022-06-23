package com.compass.shopstyle.services;

import com.compass.shopstyle.dto.ChangePasswordDTO;
import com.compass.shopstyle.dto.CustomerDTO;
import com.compass.shopstyle.dto.CustomerFormDTO;
import com.compass.shopstyle.dto.CustomerLoginFormDTO;
import com.compass.shopstyle.dto.CustomerNewFormDTO;

public interface CustomerService {
	
	CustomerDTO insert(CustomerFormDTO customerObj);
	
	CustomerDTO findById(Long id);
	
	CustomerDTO update(Long id, CustomerNewFormDTO customerObj);
	
	CustomerDTO login(CustomerLoginFormDTO customerLoginObj);
	
	CustomerDTO changePassword(Long id, ChangePasswordDTO changePasswordObj);
}
