package com.compass.msbffshop.feignclients.request.catalog;

import java.io.Serializable;
import java.util.List;

import com.compass.msbffshop.feignclients.response.Sku;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String description;
	private String brand;
	private String material;
	private Boolean active;
	private List<Sku> images;
	
}

