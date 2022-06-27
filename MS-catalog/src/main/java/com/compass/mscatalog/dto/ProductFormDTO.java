package com.compass.mscatalog.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.compass.mscatalog.entities.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductFormDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long  id;
	@NotBlank(message = "obligatory field")
	private String name;
	@NotBlank(message = "obligatory field")
	private String description;
	@NotBlank(message = "obligatory field")
	private String brand;
	@NotBlank(message = "obligatory field")
	private String material;
	@NotNull(message = "obligatory field")
	private Boolean active;
	@NotBlank(message = "Category id is an obligatory field")
	private Long categoryId;
	
	
	public ProductFormDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.brand = product.getBrand();
		this.material = product.getMaterial();
		this.active = product.getActive();
		this.categoryId = product.getCategoryId().getId();
	}
	
}
