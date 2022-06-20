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

import com.compass.mscatalog.dto.ProductDTO;
import com.compass.mscatalog.dto.ProductFormDTO;
import com.compass.mscatalog.services.ProductService;

@RestController
@RequestMapping(value = "/v1")
public class ProductController {
	
	
	@Autowired
	private ProductService productService;

	@PostMapping(value = "/products")
	@Transactional
	public ResponseEntity<ProductDTO> insert (@RequestBody ProductFormDTO productObj) {
		ProductDTO product = productService.insert(productObj);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> list = productService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/products/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		ProductDTO product = productService.findById(id);
		return ResponseEntity.ok().body(product);
	}
	
	@DeleteMapping(value = "/products/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/products/{id}")
	@Transactional
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductFormDTO productObj) {
		ProductDTO product = productService.update(id, productObj);
		return ResponseEntity.ok(product);
	}

}

