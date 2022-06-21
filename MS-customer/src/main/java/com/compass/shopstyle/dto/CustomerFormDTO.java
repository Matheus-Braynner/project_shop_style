package com.compass.shopstyle.dto;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.compass.shopstyle.entities.Customer;
import com.compass.shopstyle.entities.enums.Gender;
import com.compass.shopstyle.services.validation.CustomerInsert;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@CustomerInsert
public class CustomerFormDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "obligatory field")
	@Length(min = 3, message = "the size have to be between 5 and 20 characters")
	private String firstName;
	@NotBlank(message = "obligatory field")
	@Length(min = 3, message = "the size have to be between 5 and 20 characters")
	private String lastName;
	@NotNull(message = "obligatory field")
	@Enumerated(EnumType.STRING)
	private Gender sex;
	@NotBlank(message = "obligatory field")
	private String cpf;
	@NotNull(message = "obligatory field")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthDate;
	@NotBlank(message = "obligatory field")
	@javax.validation.constraints.Email(message = "Email invalid")
	private String email;
	@NotBlank(message = "obligatory field")
	private String password;
	@NotNull(message = "obligatory field")
	private Boolean active;

	public CustomerFormDTO(Customer customer) {
		this.setId(null);
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.sex = customer.getSex();
		this.cpf = customer.getCpf();
		this.birthDate = customer.getBirthDate();
		this.email = customer.getEmail();
		this.password = customer.getPassword();
		this.active = customer.getActive();
	}	
}
