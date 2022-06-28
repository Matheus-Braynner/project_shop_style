package com.compass.payment.dto;

import java.io.Serializable;

import com.compass.payment.entities.Payment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String type;
	private Boolean installments;
	private Boolean active;
	
	public PaymentDTO(Payment payment) {
		this.id = payment.getId();
		this.type = payment.getType();
		this.installments = payment.getInstallments();
		this.active = payment.getActive();
	}
}

