package com.compass.shopstyle.dto;

import com.compass.shopstyle.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginFormDTO {
	
	private String email;
	private String password;
	
	
	public UserLoginFormDTO (User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
	}
}
