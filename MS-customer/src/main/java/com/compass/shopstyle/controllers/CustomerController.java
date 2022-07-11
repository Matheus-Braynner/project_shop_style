package com.compass.shopstyle.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.shopstyle.dto.ChangePasswordDTO;
import com.compass.shopstyle.dto.CustomerDTO;
import com.compass.shopstyle.dto.CustomerFormDTO;
import com.compass.shopstyle.dto.CustomerLoginFormDTO;
import com.compass.shopstyle.dto.CustomerNewFormDTO;
import com.compass.shopstyle.services.CustomerService;

@RestController
@RequestMapping(value = "/v1/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	
	@PostMapping()
	@Transactional
	public ResponseEntity<CustomerDTO> insert(@Valid @RequestBody CustomerFormDTO customerBody) {
		CustomerDTO customer = customerService.insert(customerBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}
	
	@PostMapping(value = "/login")
	@Transactional
	public ResponseEntity<CustomerDTO> login(@Valid @RequestBody CustomerLoginFormDTO customerLoginBody) {
		CustomerDTO customer = customerService.login(customerLoginBody);
		return ResponseEntity.ok().body(customer);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
		CustomerDTO customer = customerService.findById(id);
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @Valid @RequestBody CustomerNewFormDTO customerBody) {
		CustomerDTO customer = customerService.update(id, customerBody);
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping(value = "/login/{id}")
	@Transactional
	public ResponseEntity<CustomerDTO> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordDTO changePasswordBody) {
		CustomerDTO customer = customerService.changePassword(id, changePasswordBody);
		return ResponseEntity.ok(customer);
	}
	
	
}
