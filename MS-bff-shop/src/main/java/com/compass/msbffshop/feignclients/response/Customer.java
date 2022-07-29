package com.compass.msbffshop.feignclients.response;

import java.io.Serializable;
import java.util.Date;

import com.compass.msbffshop.feignclients.request.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String firstName;
	private String lastName;
	private Gender sex;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthDate;
	private String email;
}
