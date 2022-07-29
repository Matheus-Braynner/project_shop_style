package com.compass.msbffshop.feignclients.request.order;

import java.io.Serializable;

import java.util.List;

import com.compass.msbffshop.feignclients.request.catalog.SkuDTO;
import com.compass.msbffshop.feignclients.request.payment.PaymentDTO;

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
