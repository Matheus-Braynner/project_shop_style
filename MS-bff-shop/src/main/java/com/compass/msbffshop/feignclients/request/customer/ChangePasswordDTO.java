package com.compass.msbffshop.feignclients.request.customer;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "obligatory field")
	@javax.validation.constraints.Email(message = "Email invalid")
	private String email;
	@NotBlank(message = "obligatory field")
	@CPF
	private String cpf;
	@NotBlank(message = "obligatory field")
	@Length(min = 6, message = "the minimum number of characters is 6")
	private String oldPassword;
	@NotBlank(message = "obligatory field")
	@Length(min = 6, message = "the minimum number of characters is 6")
	private String newPassword;
	@NotBlank(message = "obligatory field")
	@Length(min = 6, message = "the minimum number of characters is 6")
	private String newPasswordConfirmation;
}
