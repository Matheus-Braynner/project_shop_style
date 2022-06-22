package com.compass.shopstyle.services;

import java.util.List;

import com.compass.shopstyle.dto.AddressDTO;
import com.compass.shopstyle.dto.AddressFormDTO;

public interface AddressService {

	AddressDTO insert (AddressFormDTO addressObj);
	
	List<AddressDTO> findAll();
	
	AddressDTO update(Long id, AddressFormDTO addressObj);
	
	void delete(Long id);
}
