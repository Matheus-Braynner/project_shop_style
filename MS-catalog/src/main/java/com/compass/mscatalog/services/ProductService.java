package com.compass.mscatalog.services;

import java.util.List;

import com.compass.mscatalog.entities.Product;

public interface ProductService {
	
	List<Product> findAll();
}
