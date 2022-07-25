package com.compass.order.config.connections.entity;

import java.io.Serializable;

import com.compass.order.enums.Status;

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
