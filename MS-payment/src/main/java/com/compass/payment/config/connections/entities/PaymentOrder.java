package com.compass.payment.config.connections.entities;

import java.io.Serializable;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	private String orderId;
	private PaymentDTO payment;

}
