package com.compass.msbffshop.controllers;

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

import com.compass.msbffshop.feignclients.CustomerClient;
import com.compass.msbffshop.feignclients.request.customer.ChangePasswordDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerFormDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerLoginFormDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerNewFormDTO;

@RestController
@RequestMapping(value = "/bffshop")
public class BffShopController {

	@Autowired
	private CustomerClient customerClient;
	
	@PostMapping(value = "/v1/customers/login")
	@Transactional
	public ResponseEntity<CustomerDTO> login(@Valid @RequestBody CustomerLoginFormDTO customerLoginBody) {
		CustomerDTO customer = customerClient.login(customerLoginBody);
		return ResponseEntity.ok().body(customer);
	}
	
	@PutMapping(value = "/v1/customers/login/{id}")
	@Transactional
	public ResponseEntity<CustomerDTO> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordDTO changePasswordBody) {
		CustomerDTO customer = customerClient.changePassword(id, changePasswordBody);
		return ResponseEntity.ok(customer);
	}
	
	
	@PostMapping(value = "/v1/customers")
	@Transactional
	public ResponseEntity<CustomerDTO> insert(@Valid @RequestBody CustomerFormDTO customerBody) {
		CustomerDTO customer = customerClient.insert(customerBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}
	
	@GetMapping(value = "/v1/customers/{id}")
	public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
		CustomerDTO customer = customerClient.findById(id);
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping(value = "/v1/customers/{id}")
	public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @Valid @RequestBody CustomerNewFormDTO customerBody) {
		CustomerDTO customer = customerClient.update(id, customerBody);
		return ResponseEntity.ok(customer);
	}
	
	
	
}
