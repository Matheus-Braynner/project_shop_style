package com.compass.order.feignclients.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Installment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer amount;
	private String brand;
	private Payment payment;
	
}
