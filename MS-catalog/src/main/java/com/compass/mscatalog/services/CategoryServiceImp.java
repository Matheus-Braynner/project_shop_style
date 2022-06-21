package com.compass.mscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.compass.mscatalog.dto.CategoryDTO;
import com.compass.mscatalog.dto.CategoryFormDTO;
import com.compass.mscatalog.entities.Category;
import com.compass.mscatalog.repositories.CategoryRepository;
import com.compass.mscatalog.services.exception.DatabaseException;
import com.compass.mscatalog.services.exception.ObjectNotFoundException;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceService;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public CategoryDTO insert(CategoryFormDTO categoryBody) {
		categoryBody.setId(sequenceService.getSequenceNumber(Category.SEQUENCE_NAME));
		Category category = categoryRepository.save(mapper.map(categoryBody, Category.class));
		return mapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> findAll() {
		List<Category> list = categoryRepository.findAll();
		List<CategoryDTO> listDTO = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		return listDTO;
	}

	@Override
	public void delete(Long id) {
		try {
			Category category = categoryRepository.findById(id)
					.orElseThrow(() -> new ObjectNotFoundException("Object not found, id : " + id));
			categoryRepository.delete(category);
			} catch (EmptyResultDataAccessException e) {
				throw new ObjectNotFoundException("Object not found " + id);
			} catch (DatabaseException e) {
				throw new DatabaseException(e.getMessage());
			}
	}

	@Override
	public CategoryDTO update(Long id, CategoryFormDTO categoryBody) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Object not found, id : " + id));
		category.setName(categoryBody.getName());
		category.setActive(categoryBody.getActive());
		Category categoryUpdated = categoryRepository.save(category);
		return mapper.map(categoryUpdated, CategoryDTO.class);
	}


}
