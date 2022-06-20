package com.compass.mscatalog.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	private String id;
	private String name;
	private String description;
	private Boolean active;
	
	public Product(String id, String name, String description, Boolean active) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.active = active;
	}
	
	

}
