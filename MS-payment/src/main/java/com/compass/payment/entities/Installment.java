package com.compass.payment.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.compass.payment.dto.InstallmentFormDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Installment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private Double amount;
	private String brand;
	@OneToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	public Installment(InstallmentFormDTO installmentFormDto, Payment payment) {
		this.amount = installmentFormDto.getAmount();
		this.brand = installmentFormDto.getBrand();
		this.payment = payment;
	}
}
