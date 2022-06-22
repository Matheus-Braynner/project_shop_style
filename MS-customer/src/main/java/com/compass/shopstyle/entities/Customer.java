package com.compass.shopstyle.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.compass.shopstyle.entities.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String firstName;
	private String lastName;
	@Enumerated(EnumType.STRING)
	private Gender sex;
	private String cpf;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthDate;
	@Column(unique = true)
	@javax.validation.constraints.Email
	private String email;
	@JsonIgnore
	private String password;
	private Boolean active;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Address> adresses = new ArrayList<>();

}
