package com.compass.payment.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.compass.payment.dto.InstallmentDTO;
import com.compass.payment.dto.InstallmentFormDTO;
import com.compass.payment.entities.Installment;
import com.compass.payment.entities.Payment;
import com.compass.payment.repositories.InstallmentRepository;
import com.compass.payment.repositories.PaymentRepository;
import com.compass.payment.services.exceptions.DatabaseException;
import com.compass.payment.services.exceptions.NotValidPaymentException;
import com.compass.payment.services.exceptions.ResourceNotFoundException;

@Service
public class InstallmentServiceImp implements InstallmentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private InstallmentRepository installmentRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public InstallmentDTO insert(InstallmentFormDTO installmentObj) {
		Payment payment = paymentRepository.findById(installmentObj.getPaymentId())
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found, Installment id = " + installmentObj.getPaymentId()));
		
		if(payment.getActive() && payment.getInstallments()) {
			Installment installment = new Installment(installmentObj, payment);
			Installment installmentSaved = installmentRepository.save(mapper.map(installment, Installment.class));
			return mapper.map(installmentSaved, InstallmentDTO.class);
		}
		
		throw new NotValidPaymentException("Invalid payment!");
	}

	@Override
	public InstallmentDTO update(Long id, InstallmentFormDTO installmentObj) {
		Payment payment = paymentRepository.findById(installmentObj.getPaymentId())
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found, Installment id = " + id));
		Installment installment = installmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found, Installment id = " + id));
		installment.setAmount(installmentObj.getAmount());
		installment.setBrand(installmentObj.getBrand());
		installment.setPayment(payment);
		
		Installment installmentSaved = installmentRepository.save(installment);
		return mapper.map(installmentSaved, InstallmentDTO.class);
	}

	@Override
	public void delete(Long id) {
		try {
			Installment installment = installmentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Resource not found, Installment id = " + id));
			installmentRepository.delete(installment);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Resource not found, Installment id = " + id);
		} catch (DatabaseException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

}
