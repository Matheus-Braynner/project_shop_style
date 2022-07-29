package com.compass.msbffshop.feignclients.request.customer;

import java.io.Serializable;

import com.compass.msbffshop.feignclients.request.enums.States;

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

}
