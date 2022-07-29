package com.compass.msbffshop.feignclients.request.catalog;

import java.io.Serializable;
import java.util.List;
import java.util.Locale.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Boolean active;
	private List<Category> children;
	
}
