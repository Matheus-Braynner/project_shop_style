package com.compass.msclaudit.feignclients.requests;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.compass.msclaudit.feignclients.response.Address;
import com.compass.msclaudit.feignclients.response.Customer;
import com.compass.msclaudit.feignclients.response.Installment;
import com.compass.msclaudit.feignclients.response.Order;
import com.compass.msclaudit.feignclients.response.Payment;
import com.compass.msclaudit.feignclients.response.Sku;
import com.compass.msclaudit.feignclients.response.Status;

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
	
	public OrderDTO(Order order) {
		this.id = order.getId();
		this.customer = order.getCustomer();
		this.payment = order.getPayment();
		this.cart = order.getCart();
		this.installment = order.getInstallment();
		this.date = order.getDate();
		this.status = order.getStatus();
		this.total = order.getTotal();
		this.address = order.getAddress();
	}

}
