package com.compass.payment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDTO {

	private Long id;
	private String type;
	private Boolean installments;
	private Boolean active;
}
