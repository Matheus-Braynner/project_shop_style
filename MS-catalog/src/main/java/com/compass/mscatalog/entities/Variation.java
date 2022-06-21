package com.compass.mscatalog.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.compass.mscatalog.entities.enums.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Variation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	public static final String SEQUENCE_NAME="variations_sequence";
	
		@Id
		@EqualsAndHashCode.Include
		private Long id;
		private String color;
		private Size size;
		private Double price;
		private Integer quantity;
		
		
}
