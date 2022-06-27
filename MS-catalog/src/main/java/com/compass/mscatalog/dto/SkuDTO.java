package com.compass.mscatalog.dto;

import java.io.Serializable;
import java.util.List;

import com.compass.mscatalog.entities.Media;
import com.compass.mscatalog.entities.Sku;
import com.compass.mscatalog.entities.enums.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SkuDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Double price;
	private Integer quantity;
	private String color;
	private Size size;
	private Integer height;
	private Integer width;
	private List<Media> images;
	
	public SkuDTO(Sku sku) {
		this.id = sku.getId();
		this.price = sku.getPrice();
		this.quantity = sku.getQuantity();
		this.color = sku.getColor();
		this.size = sku.getSize();
		this.height = sku.getHeight();
		this.width = sku.getWidth();
		this.images = sku.getImages();
	}
	
}
