package com.compass.msclaudit.feignclients.requests;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFormDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private CustomerDTO customer;
	private PaymentDTO payment;
	private  List<SkuDTO> cart;

}
