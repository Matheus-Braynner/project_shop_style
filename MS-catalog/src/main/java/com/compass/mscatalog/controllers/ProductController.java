package com.compass.mscatalog.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.mscatalog.entities.Product;

@RestController
@RequestMapping(value = "/v1")
public class ProductController {

	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> findAll() {
		Product p1 = new Product("1", "Camisa", "Camisa do Sport", true);
		Product p2 = new Product("2", "Short", "Short do Sport", true);
		List<Product> list = new ArrayList<>();
		list.addAll(Arrays.asList(p1, p2));
		return ResponseEntity.ok().body(list);
	}
}
