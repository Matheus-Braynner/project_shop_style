package com.compass.payment.config.connections.entities;

import java.io.Serializable;

import com.compass.payment.config.connections.entities.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String orderId;
	private Status status;

}
