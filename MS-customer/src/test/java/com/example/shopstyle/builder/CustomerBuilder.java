package com.example.shopstyle.builder;

import java.util.Date;

import com.compass.shopstyle.dto.CustomerDTO;
import com.compass.shopstyle.dto.CustomerFormDTO;
import com.compass.shopstyle.dto.CustomerLoginFormDTO;
import com.compass.shopstyle.dto.CustomerNewFormDTO;
import com.compass.shopstyle.entities.Customer;
import com.compass.shopstyle.entities.enums.Gender;

public final class CustomerBuilder {
	
	public static final Long ID = 1L;
	public static final Date BIRTHDAY = new Date();
	
	public static CustomerFormDTO getCustomerFormDTO() {
		CustomerFormDTO customerFormDTO = new CustomerFormDTO();
		customerFormDTO.setId(ID);
		customerFormDTO.setFirstName("Matheus");
		customerFormDTO.setLastName("Souza");
		customerFormDTO.setSex(Gender.MAN);
		customerFormDTO.setCpf("28006222029");
		customerFormDTO.setBirthDate(BIRTHDAY);
		customerFormDTO.setEmail("matheus@email.com");
		customerFormDTO.setPassword("123456");
		customerFormDTO.setActive(true);
		return customerFormDTO;
	}
	
	
	public static CustomerDTO getCustomerDTO() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(ID);
		customerDTO.setFirstName("Matheus");
		customerDTO.setLastName("Souza");
		customerDTO.setSex(Gender.MAN);
		customerDTO.setBirthDate(BIRTHDAY);
		customerDTO.setEmail("matheus@email.com");
		return customerDTO;
	}
	
	
	public static Customer getCustomer() {
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setFirstName("Matheus");
		customer.setLastName("Souza");
		customer.setSex(Gender.MAN);
		customer.setCpf("28006222029");
		customer.setBirthDate(BIRTHDAY);
		customer.setEmail("matheus@email.com");
		customer.setPassword("123456");
		customer.setActive(true);
		return customer;
	}
	
	public static CustomerNewFormDTO getCustomerNewFormDTO() {
		CustomerNewFormDTO customerNewFormDTO = new CustomerNewFormDTO();
		customerNewFormDTO.setId(ID);
		customerNewFormDTO.setFirstName("Matheus");
		customerNewFormDTO.setLastName("Souza");
		customerNewFormDTO.setSex(Gender.MAN);
		customerNewFormDTO.setCpf("28006222029");
		customerNewFormDTO.setBirthDate(BIRTHDAY);
		customerNewFormDTO.setEmail("matheus@email.com");
		customerNewFormDTO.setPassword("123456");
		customerNewFormDTO.setActive(true);
		return customerNewFormDTO;
	}
	
	public static CustomerLoginFormDTO getCustomerLogin() {
		CustomerLoginFormDTO customerLoginFormDTO = new CustomerLoginFormDTO();
		customerLoginFormDTO.setEmail("matheus@email.com");
		customerLoginFormDTO.setPassword("123456");
		return customerLoginFormDTO;
	}

}
