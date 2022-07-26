package com.compass.msclaudit.feignclients.response;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address implements Serializable {
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
