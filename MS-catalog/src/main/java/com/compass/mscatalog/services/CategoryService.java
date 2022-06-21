package com.compass.mscatalog.services;

import java.util.List;

import com.compass.mscatalog.dto.CategoryDTO;
import com.compass.mscatalog.dto.CategoryFormDTO;

public interface CategoryService {

	CategoryDTO insert(CategoryFormDTO categoryBody);
	
	List<CategoryDTO> findAll();
	
	void delete(Long id);
	
	CategoryDTO update (Long id, CategoryFormDTO categoryBody);
}
