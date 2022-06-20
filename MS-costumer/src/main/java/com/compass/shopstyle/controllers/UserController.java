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

import com.compass.shopstyle.dto.UserDTO;
import com.compass.shopstyle.dto.UserFormDTO;
import com.compass.shopstyle.dto.UserNewFormDTO;
import com.compass.shopstyle.services.UserServiceImp;

@RestController
@RequestMapping(value = "/v1")
public class UserController {
	
	@Autowired
	private UserServiceImp userService;

	
	@PostMapping(value = "/users")
	@Transactional
	@CacheEvict(value = "getUsers", allEntries = true)
	public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserFormDTO userForm) {
		UserDTO user = userService.insert(userForm);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@GetMapping(value = "/{id}")
	@Cacheable(value = "getUsers")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		UserDTO user = userService.findById(id);
		return ResponseEntity.ok(user);
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	@CacheEvict(value = "getUsers", allEntries = true)
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserNewFormDTO userForm) {
		UserDTO user = userService.update(id, userForm);
		return ResponseEntity.ok(user);
	}
	
}
