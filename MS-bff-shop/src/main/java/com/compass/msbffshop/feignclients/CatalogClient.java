package com.compass.msbffshop.feignclients;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.compass.msbffshop.feignclients.request.catalog.CategoryDTO;
import com.compass.msbffshop.feignclients.request.catalog.ProductDTO;

@Component
@FeignClient("MS-catalog")
public interface CatalogClient {

	
	@GetMapping(value = "/v1/products")
	List<ProductDTO> findAll();
	
	@GetMapping(value = "/v1/products/{id}")
	ProductDTO findById(@PathVariable Long id);
	
	@GetMapping(value = "/v1/categories")
	List<CategoryDTO> findAllCategories();
	
	@GetMapping(value = "/v1/categories/{id}")
	List<ProductDTO> findProductsByIdCategory(@PathVariable Long id);
}
