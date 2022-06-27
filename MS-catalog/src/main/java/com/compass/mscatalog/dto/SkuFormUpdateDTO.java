package com.compass.mscatalog.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.compass.mscatalog.entities.Media;
import com.compass.mscatalog.entities.enums.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SkuFormUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "obligatory field")
	private Double price;
	@NotBlank(message = "obligatory field")
	private Integer quantity;
	@NotBlank(message = "obligatory field")
	private String color;
	@NotNull(message = "obligatory field")
	private Size size;
	@NotBlank(message = "height requires to be in centimeters")
	private Integer height;
	@NotBlank(message = "width requires to be in centimeters")
	private Integer width;
	@NotNull(message = "obligatory field")
	private List<Media> images;
	@NotBlank(message = "Product id is an obligatory field")
	private Long productId;
	
	
	
}
