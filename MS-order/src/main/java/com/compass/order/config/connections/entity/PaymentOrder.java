package com.compass.order.config.connections.entity;

import java.io.Serializable;

import com.compass.order.feignclients.request.PaymentDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String orderId;
	private PaymentDTO payment;
}
