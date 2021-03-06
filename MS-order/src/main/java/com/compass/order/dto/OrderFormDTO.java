package com.compass.order.dto;

import java.io.Serializable;
import java.util.List;

import com.compass.order.feignclients.request.CustomerDTO;
import com.compass.order.feignclients.request.PaymentDTO;
import com.compass.order.feignclients.request.SkuDTO;

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
