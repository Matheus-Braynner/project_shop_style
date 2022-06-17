package com.compass.shopstyle.services;

import com.compass.shopstyle.dto.UserDTO;
import com.compass.shopstyle.dto.UserFormDTO;
import com.compass.shopstyle.dto.UserNewFormDTO;

public interface UserService {
	
	UserDTO insert(UserFormDTO userBody);
	
	UserDTO findById(Long id);
	
	UserDTO update(Long id, UserNewFormDTO userBody);
}
