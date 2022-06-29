package com.compass.order.feignclients.request;

import java.io.Serializable;

import com.compass.order.feignclients.response.Address;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private States state;
	private String city;
	private String district;
	private String street;
	private String number;
	private String cep;
	private String complement;

	
	public AddressDTO (Address address) {
		this.id = address.getId();
		this.state = address.getState();
		this.city = address.getCity();
		this.district = address.getDistrict();
		this.street = address.getStreet();
		this.number = address.getNumber();
		this.cep = address.getCep();
		this.complement = address.getComplement();
	}
}
