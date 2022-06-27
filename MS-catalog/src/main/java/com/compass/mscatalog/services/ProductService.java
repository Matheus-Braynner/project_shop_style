package com.compass.mscatalog.services;

import java.util.List;

import com.compass.mscatalog.dto.ProductDTO;
import com.compass.mscatalog.dto.ProductFormDTO;

public interface ProductService {
	
	ProductDTO insert (ProductFormDTO productObj);
	
	List<ProductDTO> findAll();
	
	ProductDTO findById(Long id);
	
	void delete(Long id);
	
	ProductDTO update(Long id, ProductFormDTO productObj);
}
	