package com.compass.shopstyle.dto;

import java.io.Serializable;

import com.compass.shopstyle.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginFormDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	
	
	public UserLoginFormDTO (User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
	}
}
