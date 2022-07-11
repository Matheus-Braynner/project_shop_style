package com.compass.payment.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstallmentFormDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "obligatory field")
	private Integer amount;
	private String brand;
	@NotNull(message = "obligatory field")
	private Long paymentId;
}
