package com.compass.mscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.compass.mscatalog.dto.ProductDTO;
import com.compass.mscatalog.dto.ProductFormDTO;
import com.compass.mscatalog.entities.Product;
import com.compass.mscatalog.repositories.ProductRepository;
import com.compass.mscatalog.services.exception.DatabaseException;
import com.compass.mscatalog.services.exception.ObjectNotFoundException;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public ProductDTO insert(ProductFormDTO productObj) {
		productObj.setId(null);
		Product product = productRepository.save(mapper.map(productObj, Product.class));
		return mapper.map(product, ProductDTO.class);
	}
	
	@Override
	public List<ProductDTO> findAll() {
		List<Product> listAll = productRepository.findAll();
		List<ProductDTO> listDTO = listAll.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		return listDTO;
	}

	@Override
	public ProductDTO findById(String id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Object not found " + id));
		return mapper.map(product, ProductDTO.class);
	}

	@Override
	public void delete(String id) {
		try {
			Product product = productRepository.findById(id)
		
				.orElseThrow(() -> new ObjectNotFoundException(id));
			productRepository.delete(product);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		} catch (DatabaseException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	

}
