package com.compass.msbffshop.feignclients.request.customer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.compass.msbffshop.feignclients.request.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerFormDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "obligatory field")
	@Length(min = 3, message = "the minimum number of characters is 3")
	private String firstName;
	@NotBlank(message = "obligatory field")
	@Length(min = 3, message = "the minimum number of characters is 3")
	private String lastName;
	@NotNull(message = "obligatory field")
	@Enumerated(EnumType.STRING)
	private Gender sex;
	@NotBlank(message = "obligatory field")
	@CPF
	private String cpf;
	@NotNull(message = "obligatory field")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthDate;
	@NotBlank(message = "obligatory field")
	@javax.validation.constraints.Email(message = "Email invalid")
	private String email;
	@NotBlank(message = "obligatory field")
	@Length(min = 6, message = "the minimum number of characters is 6")
	private String password;
	@NotNull(message = "obligatory field")
	private Boolean active;
}
