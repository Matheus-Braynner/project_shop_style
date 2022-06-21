package com.compass.shopstyle.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.compass.shopstyle.dto.CustomerDTO;
import com.compass.shopstyle.dto.CustomerFormDTO;
import com.compass.shopstyle.dto.CustomerNewFormDTO;
import com.compass.shopstyle.entities.Customer;
import com.compass.shopstyle.repositories.CustomerRepository;
import com.compass.shopstyle.services.exceptions.ResourceNotFoundException;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public CustomerDTO insert(CustomerFormDTO customerObj) {
		customerObj.setId(null);
		Customer user = customerRepository.save(mapper.map(customerObj, Customer.class));
		user.setPassword(passwordEncoder.encode(customerObj.getPassword()));
		return mapper.map(user, CustomerDTO.class);
	}
	
	@Override
	public CustomerDTO findById(Long id) {
		Customer user = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		return mapper.map(user, CustomerDTO.class);
	}

	@Override
	public CustomerDTO update(Long id, CustomerNewFormDTO customerObj) {
		Customer user = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
			user.setFirstName(customerObj.getFirstName());
			user.setLastName(customerObj.getLastName());
			user.setSex(customerObj.getSex());
			user.setCpf(customerObj.getCpf());
			user.setBirthDate(customerObj.getBirthDate());
			user.setEmail(customerObj.getEmail());
			user.setPassword(passwordEncoder.encode(customerObj.getPassword()));
			Customer userUpdated = customerRepository.save(user);
			return mapper.map(userUpdated, CustomerDTO.class);
			
	}
}
