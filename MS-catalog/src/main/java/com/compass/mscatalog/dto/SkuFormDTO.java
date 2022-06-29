package com.compass.mscatalog.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.compass.mscatalog.entities.enums.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SkuFormDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "obligatory field")
	private Double price;
	@NotNull(message = "obligatory field")
	private Integer quantity;
	@NotBlank(message = "obligatory field")
	private String color;
	@NotNull(message = "obligatory field")
	private Size size;
	@NotNull(message = "height requires to be in centimeters")
	private Integer height;
	@NotNull(message = "width requires to be in centimeters")
	private Integer width;
	@NotNull(message = "obligatory field")
	private List<String> images;
	@NotNull(message = "Product id is an obligatory field")
	private Long productId;
	
	
	
}
