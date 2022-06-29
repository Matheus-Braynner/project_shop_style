package com.compass.mscatalog.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductFormDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	@NotNull(message = "Category id is an obligatory field")
	private Long categoryId;
	
}
