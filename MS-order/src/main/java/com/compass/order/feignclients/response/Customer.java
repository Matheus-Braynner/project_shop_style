package com.compass.order.feignclients.response;

import java.io.Serializable;
import java.util.Date;

import com.compass.order.feignclients.request.Gender;

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
	private String cpf;
	private Date birthDate;
	private String email;
	private String password;
	private Boolean active;
	private Long addressId;

}
