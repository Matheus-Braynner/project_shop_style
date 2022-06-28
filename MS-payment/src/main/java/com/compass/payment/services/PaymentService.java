package com.compass.payment.services;

import java.util.List;

import com.compass.payment.dto.PaymentDTO;
import com.compass.payment.dto.PaymentFormDTO;

public interface PaymentService {

	PaymentDTO insert(PaymentFormDTO paymentObj);
	
	List<PaymentDTO> findAll();
	
	PaymentDTO findById(Long id);
	
	void delete(Long id);
}
