package com.compass.mscatalog.entities;

import java.io.Serializable;

import com.compass.mscatalog.entities.enums.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	private String id;
	private String color;
	private Size size;
	private Integer quantity;
	
	public Category(String id, String color, Size size, Integer quantity) {
		this.id = id;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
	}
	
	

}
