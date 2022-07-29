package com.compass.msbffshop.feignclients.request.customer;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.compass.msbffshop.feignclients.request.enums.States;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressFormDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotNull(message = "obligatory field")
	private States state;
	@NotBlank(message = "obligatory field")
	private String city;
	@NotBlank(message = "obligatory field")
	private String district;
	@NotBlank(message = "obligatory field")
	private String street;
	@NotBlank(message = "obligatory field")
	private String number;
	@NotBlank(message = "obligatory field")
	private String cep;
	private String complement;
	private Long customerId;
			
}
