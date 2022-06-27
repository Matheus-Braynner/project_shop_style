package com.compass.mscatalog.entities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.compass.mscatalog.entities.enums.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sku implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double price;
	private Integer quantity;
	private String color;
	@Enumerated(EnumType.STRING)
	private Size size;
	private Integer height;
	private Integer width;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product productId;
	@OneToMany(mappedBy = "sku", fetch = FetchType.EAGER)
	private List<Media> images = new ArrayList<>();

}
