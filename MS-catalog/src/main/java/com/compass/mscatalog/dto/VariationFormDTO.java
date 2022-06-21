package com.compass.mscatalog.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.compass.mscatalog.entities.Variation;
import com.compass.mscatalog.entities.enums.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VariationFormDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "obligatory field")
	private String color;
	@NotNull(message = "Precisa preencher com o tipo da bolsa")
	private Size size;
	@NotNull(message = "Precisa preencher o campo")
	private Double price;
	@NotNull(message = "Precisa preencher o campo")
	private Integer quantity;
	
	public VariationFormDTO(Variation variation) {
		this.id = variation.getId();
		this.color = variation.getColor();
		this.size = variation.getSize();
		this.price = variation.getPrice();
		this.quantity = variation.getQuantity();
	}

}
