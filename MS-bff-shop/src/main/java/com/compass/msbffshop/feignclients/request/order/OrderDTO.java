package com.compass.msbffshop.feignclients.request.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.compass.msbffshop.feignclients.request.enums.Status;
import com.compass.msbffshop.feignclients.response.Address;
import com.compass.msbffshop.feignclients.response.Customer;
import com.compass.msbffshop.feignclients.response.Installment;
import com.compass.msbffshop.feignclients.response.Payment;
import com.compass.msbffshop.feignclients.response.Sku;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Customer customer;
	private Payment payment;
	private List<Sku> cart;
	private Installment installment;
	private Date date;
	private Status status;
	private Double total;
	private Address address;
	

}
