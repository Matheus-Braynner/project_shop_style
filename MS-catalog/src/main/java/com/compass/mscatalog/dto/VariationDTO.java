package com.compass.mscatalog.dto;

import java.io.Serializable;

import com.compass.mscatalog.entities.Variation;
import com.compass.mscatalog.entities.enums.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VariationDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String color;
	private Size size;
	private Double price;
	private Integer quantity;
	
	public VariationDTO(Variation variation) {
		this.id = variation.getId();
		this.color = variation.getColor();
		this.size = variation.getSize();
		this.price = variation.getPrice();
		this.quantity = variation.getQuantity();
	}
}
