package com.compass.mscatalog.services;

import java.util.List;

import com.compass.mscatalog.dto.CategoryDTO;
import com.compass.mscatalog.dto.CategoryFormDTO;
import com.compass.mscatalog.dto.ProductDTO;

public interface CategoryService {

	CategoryDTO insert(CategoryFormDTO categoryBody);
	
	List<CategoryDTO> findAll();
	
	List<ProductDTO> findProductsByIdCategory(Long id);
	
	void delete(Long id);
	
	CategoryDTO update (Long id, CategoryFormDTO categoryBody);
}
