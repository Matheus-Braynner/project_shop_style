package com.compass.mscatalog.services;

import java.util.List;

import com.compass.mscatalog.dto.ProductDTO;
import com.compass.mscatalog.dto.ProductFormDTO;

public interface ProductService {
	
	List<ProductDTO> findAll();
	
	ProductDTO findById(Long id);
	
	ProductDTO insert (ProductFormDTO productObj);
	
	void delete(Long id);
	
	ProductDTO update(Long id, ProductFormDTO productObj);
}
	