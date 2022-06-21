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
	@NotNull(message = "obligatory field")
	private Boolean active;
	
	public ProductFormDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.active = product.getActive();
	}
	
}
