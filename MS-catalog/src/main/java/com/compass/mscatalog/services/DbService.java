package com.compass.mscatalog.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.mscatalog.entities.Category;
import com.compass.mscatalog.repositories.CategoryRepository;

@Service
public class DbService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public void instantiateDatabase() {
		Category c1 = new Category(1L, "Camisa de time", true);
		Category c2 = new Category(1L, "Camisa de 1", true);
		Category c3 = new Category(1L, "Camisa de 2", true);
		
		categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
	}
}
