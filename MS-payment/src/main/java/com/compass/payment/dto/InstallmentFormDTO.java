package com.compass.payment.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstallmentFormDTO {

	@NotNull(message = "obligatory field")
	private Double amount;
	private String brand;
	@NotNull(message = "obligatory field")
	private Long paymentId;
}
