package com.compass.payment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstallmentFormDTO {

	private Double amount;
	private String brand;
	private Long paymentId;
}
