package com.compass.order.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.compass.order.feignclients.response.Customer;
import com.compass.order.feignclients.response.Payment;
import com.compass.order.feignclients.response.Sku;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFormDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "obligatory field")
	private Customer customer;
	@NotNull(message = "obligatory field")
	private Payment payment;
	@NotNull(message = "obligatory field")
	private  List<Sku> cart;

}
