package com.compass.payment.dto;

import com.compass.payment.entities.Payment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstallmentDTO {

	private Long id;
	private Double amount;
	private String brand;
	private Payment payment;
}
