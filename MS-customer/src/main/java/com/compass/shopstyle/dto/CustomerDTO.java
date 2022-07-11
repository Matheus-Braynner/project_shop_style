package com.compass.shopstyle.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	private Gender sex;
	private String email;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthDate;
	private List<AddressDTO> adresses;	
	
}
