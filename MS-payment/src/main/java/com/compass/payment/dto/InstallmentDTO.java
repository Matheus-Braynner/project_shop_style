package com.compass.payment.dto;

import java.io.Serializable;

import com.compass.payment.entities.Installment;
import com.compass.payment.entities.Payment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstallmentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Double amount;
	private String brand;
	private Payment payment;
	
	public InstallmentDTO(Installment installment) {
		this.id = installment.getId();
		this.amount = installment.getAmount();
		this.brand = installment.getBrand();
		this.payment = installment.getPayment();
	}
}
