package com.compass.shopstyle.dto;

import java.io.Serializable;

import com.compass.shopstyle.entities.Customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerLoginFormDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	
	
	public CustomerLoginFormDTO (Customer customer) {
		this.email = customer.getEmail();
		this.password = customer.getPassword();
	}
}
