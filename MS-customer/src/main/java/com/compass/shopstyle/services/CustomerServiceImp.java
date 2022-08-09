package com.compass.shopstyle.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.compass.shopstyle.dto.ChangePasswordDTO;
import com.compass.shopstyle.dto.CustomerDTO;
import com.compass.shopstyle.dto.CustomerFormDTO;
import com.compass.shopstyle.dto.CustomerLoginFormDTO;
import com.compass.shopstyle.dto.CustomerNewFormDTO;
import com.compass.shopstyle.entities.Customer;
import com.compass.shopstyle.repositories.CustomerRepository;
import com.compass.shopstyle.services.exceptions.EmailOrPasswordException;
import com.compass.shopstyle.services.exceptions.InvalidDataChangePasswordException;
import com.compass.shopstyle.services.exceptions.ResourceNotFoundException;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerDTO insert(CustomerFormDTO customerObj) {
		customerObj.setId(null);
		Customer user = customerRepository.save(mapper.map(customerObj, Customer.class));
		user.setPassword(new BCryptPasswordEncoder().encode(customerObj.getPassword()));
		return mapper.map(user, CustomerDTO.class);
	}

	@Override
	public CustomerDTO findById(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Customer not found, ID = " + id));
		return mapper.map(customer, CustomerDTO.class);
	}

	@Override
	public CustomerDTO update(Long id, CustomerNewFormDTO customerObj) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found, ID = " + id));
		customer.setFirstName(customerObj.getFirstName());
		customer.setLastName(customerObj.getLastName());
		customer.setSex(customerObj.getSex());
		customer.setCpf(customerObj.getCpf());
		customer.setBirthDate(customerObj.getBirthDate());
		customer.setEmail(customerObj.getEmail());
		customer.setPassword(new BCryptPasswordEncoder().encode(customerObj.getPassword()));
		Customer userUpdated = customerRepository.save(customer);
		return mapper.map(userUpdated, CustomerDTO.class);

	}

	@Override
	public CustomerDTO login(CustomerLoginFormDTO customerLoginObj) {
		Customer customer = customerRepository.findByEmail(customerLoginObj.getEmail());
		if(customer != null && new BCryptPasswordEncoder().matches(customerLoginObj.getPassword(), customer.getPassword())) {
			return mapper.map(customer, CustomerDTO.class);
		}
		throw new EmailOrPasswordException("wrong email or password");
	}
	
	@Override
	public CustomerDTO changePassword(Long id, ChangePasswordDTO changePasswordObj) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found, ID = " +  id));
		if(verifyPssword(customer, changePasswordObj)) {
			customer.setPassword(changePasswordObj.getNewPassword());
			return mapper.map(customer, CustomerDTO.class);
		}
		throw new InvalidDataChangePasswordException("invalid data entered");
	}
	
	private Boolean verifyPssword(Customer customerObj, ChangePasswordDTO changePasswordObj) {
		if(new BCryptPasswordEncoder().matches(changePasswordObj.getOldPassword(), customerObj.getPassword())
				&& changePasswordObj.getCpf().equals(customerObj.getCpf())
				&& changePasswordObj.getEmail().equals(customerObj.getEmail())
				&& changePasswordObj.getNewPassword().equals(changePasswordObj.getNewPasswordConfirmation())) {
			return true;
		}
		return false;
	}

	@Override
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	

}
