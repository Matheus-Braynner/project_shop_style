package com.compass.msbffshop.feignclients.request.customer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.compass.msbffshop.feignclients.request.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerNewFormDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "obligatory field")
	@Length(min = 3, message = "the size have to be between 5 and 20 characters")
	private String firstName;
	@NotBlank(message = "obligatory field")
	@Length(min = 3, message = "the size have to be between 5 and 20 characters")
	private String lastName;
	@Enumerated(EnumType.STRING)
	@NotNull(message = "obligatory field")
	private Gender sex;
	@NotBlank(message = "obligatory field")
	@CPF
	private String cpf;
	@NotNull(message = "obligatory field")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthDate;
	@NotBlank(message = "obligatory field")
	@Email(message = "Email invalid")
	private String email;
	@NotBlank(message = "obligatory field")
	private String password;
	@NotNull(message = "obligatory field")
	private Boolean active;

}
