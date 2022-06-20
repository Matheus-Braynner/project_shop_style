package com.compass.mscatalog.services;

import java.util.List;

import com.compass.mscatalog.dto.ProductDTO;
import com.compass.mscatalog.entities.Product;

public interface ProductService {
	
	List<ProductDTO> findAll();
	
	ProductDTO findById(String id);
}
