package com.compass.payment.services;

import com.compass.payment.dto.InstallmentDTO;
import com.compass.payment.dto.InstallmentFormDTO;

public interface InstallmentService {

	InstallmentDTO insert(InstallmentFormDTO installmentObj);
	
	InstallmentDTO update(Long id, InstallmentFormDTO installmentObj);
	
	void delete(Long id);
	
}
