package com.compass.mscatalog.entities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Boolean active;
	@ManyToOne	@JsonIgnore
	@JoinColumn(name = "parents_id")
	private Category parents;
	@OneToMany(mappedBy = "parents")
	private List<Category> children = new ArrayList<>();
	@OneToMany(mappedBy = "categoryId")
	private List<Product> products = new ArrayList<>();
	
}
