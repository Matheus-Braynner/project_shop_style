package com.compass.shopstyle.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.compass.shopstyle.entities.User;
import com.compass.shopstyle.entities.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	@Enumerated(EnumType.STRING)
	private Gender sex;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthDate;
	private String email;
	
	public UserDTO (User user) {
		this.setId(null);
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.sex = user.getSex();
		this.birthDate = user.getBirthDate();
		this.email = user.getEmail();
	}

}
