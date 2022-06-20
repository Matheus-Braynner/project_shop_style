package com.compass.mscatalog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.mscatalog.dto.ProductDTO;
import com.compass.mscatalog.services.ProductService;

@RestController
@RequestMapping(value = "/v1")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> list = productService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/products/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable String id) {
		ProductDTO product = productService.findById(id);
		return ResponseEntity.ok().body(product);
	}
}

