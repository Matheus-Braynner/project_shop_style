package com.compass.mscatalog.controllers;

import java.util.List;

import javax.validation.Valid;

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
import com.compass.mscatalog.dto.ProductDTO;
import com.compass.mscatalog.services.CategoryServiceImp;

@RestController
@RequestMapping(value = "/v1/categories")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImp categoryService;
	
	@PostMapping()
	@Transactional
	public ResponseEntity<CategoryDTO> insert(@RequestBody @Valid CategoryFormDTO categoryBody) {
		CategoryDTO category = categoryService.insert(categoryBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	@GetMapping()
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<CategoryDTO> list = categoryService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<ProductDTO>> findProductsByIdCategory(@PathVariable Long id) {
		List<ProductDTO> products = categoryService.findProductsByIdCategory(id);
		return ResponseEntity.ok(products);
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<CategoryDTO> update (@PathVariable Long id, @RequestBody @Valid CategoryFormDTO categoryBody) {
		CategoryDTO category = categoryService.update(id, categoryBody);
		return ResponseEntity.ok(category);
	}

}
