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
		Customer user = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return mapper.map(user, CustomerDTO.class);
	}

	@Override
	public CustomerDTO update(Long id, CustomerNewFormDTO customerObj) {
		Customer custom = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		custom.setFirstName(customerObj.getFirstName());
		custom.setLastName(customerObj.getLastName());
		custom.setSex(customerObj.getSex());
		custom.setCpf(customerObj.getCpf());
		custom.setBirthDate(customerObj.getBirthDate());
		custom.setEmail(customerObj.getEmail());
		custom.setPassword(passwordEncoder.encode(customerObj.getPassword()));
		Customer userUpdated = customerRepository.save(custom);
		return mapper.map(userUpdated, CustomerDTO.class);

	}

	@Override
	public CustomerDTO login(CustomerLoginFormDTO customerLoginObj) {
		Customer customer = customerRepository.findByEmail(customerLoginObj.getEmail());
		if(!passwordEncoder.matches(customerLoginObj.getPassword(), customer.getPassword())) {
			throw new EmailOrPasswordException("wrong email or password");
		}
		return mapper.map(customer, CustomerDTO.class);
	}
	
	@Override
	public CustomerDTO changePassword(Long id, ChangePasswordDTO changePasswordObj) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		if(verifyPssword(customer, changePasswordObj)) {
			customer.setPassword(changePasswordObj.getNewPassword());
			return mapper.map(customer, CustomerDTO.class);
		}
		throw new InvalidDataChangePasswordException("invalid data entered");
	}
	
	private Boolean verifyPssword(Customer customerObj, ChangePasswordDTO changePasswordObj) {
		if(passwordEncoder.matches(changePasswordObj.getOldPassword(), customerObj.getPassword())
				&& changePasswordObj.getCpf().equals(customerObj.getCpf())
				&& changePasswordObj.getEmail().equals(customerObj.getEmail())
				&& changePasswordObj.getNewPassword().equals(changePasswordObj.getNewPasswordConfirmation())) {
			return true;
		}
		return false;
	}

	

}
