package com.compass.shopstyle.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.compass.shopstyle.entities.Address;
import com.compass.shopstyle.entities.Customer;
import com.compass.shopstyle.entities.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	@Enumerated(EnumType.STRING)
	private Gender sex;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthDate;
	private String email;
	private List<Address> adresses;
	
	public CustomerDTO (Customer customer) {
		this.setId(null);
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.sex = customer.getSex();
		this.birthDate = customer.getBirthDate();
		this.email = customer.getEmail();
		this.adresses = customer.getAdresses();
	}

}
