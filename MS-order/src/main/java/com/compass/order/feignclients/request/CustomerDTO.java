package com.compass.order.feignclients.request;

import java.io.Serializable;

import com.compass.order.feignclients.response.Address;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private Address adressesId;

}
