package com.compass.payment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentFormDTO  {

	@NotBlank(message = "obligatory field")
	private String type;
	@NotNull(message = "obligatory field")
	private Boolean installments;
	@NotNull(message = "obligatory field")
	private Boolean active;
}
