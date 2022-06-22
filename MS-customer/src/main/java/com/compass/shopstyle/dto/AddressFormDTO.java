package com.compass.shopstyle.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.compass.shopstyle.entities.Address;
import com.compass.shopstyle.entities.enums.States;

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
	
	public AddressFormDTO (Address address) {
		this.id = address.getId();
		this.state = address.getState();
		this.city = address.getCity();
		this.district = address.getDistrict();
		this.street = address.getStreet();
		this.number = address.getNumber();
		this.cep = address.getCep();
		this.complement = address.getComplement();
		this.customerId = address.getCustomerId().getId();
	}
			
}
