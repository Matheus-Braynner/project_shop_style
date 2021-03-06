package com.compass.payment.config.connections.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer Installments;

}
