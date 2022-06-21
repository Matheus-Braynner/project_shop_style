package com.compass.mscatalog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.compass.mscatalog.dto.CategoryDTO;
import com.compass.mscatalog.dto.CategoryFormDTO;
import com.compass.mscatalog.services.CategoryServiceImp;

@RestController
@RequestMapping(value = "/v1")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImp categoryService;
	
	@PostMapping(value = "/categories")
	@Transactional
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryFormDTO categoryBody) {
		CategoryDTO category = categoryService.insert(categoryBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	@GetMapping(value = "/categories")
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<CategoryDTO> list = categoryService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@DeleteMapping(value = "/categories/{id}")
	@Transactional
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/categories/{id}")
	@Transactional
	public ResponseEntity<CategoryDTO> update (@PathVariable Long id, @RequestBody CategoryFormDTO categoryBody) {
		CategoryDTO category = categoryService.update(id, categoryBody);
		return ResponseEntity.ok(category);
	}

}
