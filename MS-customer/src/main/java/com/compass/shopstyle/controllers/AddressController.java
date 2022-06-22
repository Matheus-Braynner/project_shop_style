package com.compass.shopstyle.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.shopstyle.dto.AddressDTO;
import com.compass.shopstyle.dto.AddressFormDTO;
import com.compass.shopstyle.services.AddressService;

@RestController
@RequestMapping(value = "v1")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping(value = "/address")
	@Transactional
	@CacheEvict(value = "getAdresses", allEntries = true)
	public ResponseEntity<AddressDTO> insert(@Valid @RequestBody AddressFormDTO addressBody) {
		AddressDTO address = addressService.insert(addressBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(address);
	}
	
	@GetMapping(value = "/address")
	@Cacheable(value = "getAdresses")
	public ResponseEntity<List<AddressDTO>> findAll() {
		List<AddressDTO> list = addressService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PutMapping(value = "/address/{id}")
	@Transactional
	@CacheEvict(value = "getAdresses", allEntries = true)
	public ResponseEntity<AddressDTO> update(@PathVariable Long id, @Valid @RequestBody AddressFormDTO addressBody) {
		AddressDTO address = addressService.update(id, addressBody);
		return ResponseEntity.ok(address);
	}
	
	@DeleteMapping(value = "/address/{id}")
	@Transactional
	@CacheEvict(value = "getAdresses", allEntries = true)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		addressService.delete(id);
		return ResponseEntity.noContent().build();
		
	}

}
