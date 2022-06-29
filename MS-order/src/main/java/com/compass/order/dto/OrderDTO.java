package com.compass.order.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.compass.order.entities.Order;
import com.compass.order.enums.Status;
import com.compass.order.feignclients.response.Customer;
import com.compass.order.feignclients.response.Installment;
import com.compass.order.feignclients.response.Payment;
import com.compass.order.feignclients.response.Sku;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Customer customer;
	private Payment payment;
	private  List<Sku> cart;
	private Installment installment;
	private Date date;
	private Status status;
	private Double total;
	
	public OrderDTO(Order order) {
		this.id = order.getId();
		this.customer = order.getCustomer();
		this.payment = order.getPayment();
		this.cart = order.getCart();
		this.installment = order.getInstallment();
		this.date = order.getDate();
		this.status = order.getStatus();
		this.total = order.getTotal();
	}

}
