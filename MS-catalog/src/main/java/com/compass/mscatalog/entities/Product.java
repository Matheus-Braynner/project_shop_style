package com.compass.mscatalog.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Transient
	public static final String SEQUENCE_NAME="product_sequence";
	
	@Id
	@EqualsAndHashCode.Include
	private Long id;
	private String name;
	private String description;
	private Boolean active;
}
