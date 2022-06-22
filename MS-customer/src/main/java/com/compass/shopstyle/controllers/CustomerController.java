package com.compass.shopstyle.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import com.compass.shopstyle.dto.CustomerDTO;
import com.compass.shopstyle.dto.CustomerFormDTO;
import com.compass.shopstyle.dto.CustomerNewFormDTO;
import com.compass.shopstyle.services.CustomerService;

@RestController
@RequestMapping(value = "/v1")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	
	@PostMapping(value = "/customers")
	@Transactional
	@CacheEvict(value = "getCusomers", allEntries = true)
	public ResponseEntity<CustomerDTO> insert(@Valid @RequestBody CustomerFormDTO customerBody) {
		CustomerDTO user = customerService.insert(customerBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@GetMapping(value = "/customers/{id}")
	@Cacheable(value = "getCustomers")
	public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
		CustomerDTO user = customerService.findById(id);
		return ResponseEntity.ok(user);
	}
	
	@PutMapping(value = "/customers/{id}")
	@Transactional
	@CacheEvict(value = "getCustomers", allEntries = true)
	public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @Valid @RequestBody CustomerNewFormDTO customerBody) {
		CustomerDTO user = customerService.update(id, customerBody);
		return ResponseEntity.ok(user);
	}
	
}
