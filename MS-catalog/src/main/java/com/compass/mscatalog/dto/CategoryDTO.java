package com.compass.mscatalog.dto;

import java.io.Serializable;

import com.compass.mscatalog.entities.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Boolean active;
	
	public CategoryDTO (Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.active = category.getActive();
	}
}
