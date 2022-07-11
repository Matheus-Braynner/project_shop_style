package com.compass.order.feignclients.response;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import com.compass.order.feignclients.request.States;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private States state;
	private String city;
	private String district;
	private String street;
	private String number;
	@Pattern(regexp="([0-9]{5}-[0-9]{3})")
	private String cep;
	private String complement;
}
