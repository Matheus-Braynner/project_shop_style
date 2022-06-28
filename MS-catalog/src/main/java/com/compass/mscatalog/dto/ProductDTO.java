package com.compass.mscatalog.dto;

import java.io.Serializable;
import java.util.List;

import com.compass.mscatalog.entities.Product;
import com.compass.mscatalog.entities.Sku;

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
	
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.brand = product.getBrand();
		this.material = product.getMaterial();
		this.active = product.getActive();
		this.images = product.getSkus();
	}
}

