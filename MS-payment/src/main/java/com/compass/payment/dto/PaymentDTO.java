package com.compass.payment.dto;

import com.compass.payment.entities.Payment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDTO {

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

