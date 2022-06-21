package com.compass.mscatalog.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.compass.mscatalog.entities.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryFormDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "obligatory field")
	private String name;
	@NotNull(message = "obligatory field")
	private Boolean active;
	
	public CategoryFormDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.active = category.getActive();
	}

}
