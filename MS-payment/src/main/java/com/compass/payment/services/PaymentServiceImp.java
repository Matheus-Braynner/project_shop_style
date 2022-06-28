package com.compass.payment.services;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.ResourceClosedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.compass.payment.dto.PaymentDTO;
import com.compass.payment.dto.PaymentFormDTO;
import com.compass.payment.entities.Payment;
import com.compass.payment.repositories.PaymentRepository;
import com.compass.payment.services.exceptions.DatabaseException;
import com.compass.payment.services.exceptions.ResourceNotFoundException;

@Service
public class PaymentServiceImp implements PaymentService {
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public PaymentDTO insert(PaymentFormDTO paymentObj) {
		Payment payment = paymentRepository.save(mapper.map(paymentObj, Payment.class));
		return mapper.map(payment, PaymentDTO.class);
	}

	@Override
	public List<PaymentDTO> findAll() {
		List<Payment> listAll = paymentRepository.findAll();
		List<PaymentDTO> listDTO = listAll.stream().map(x -> new PaymentDTO(x)).collect(Collectors.toList());
		return listDTO;
	}

	@Override
	public PaymentDTO findById(Long id) {
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found, Payment id = " + id));
		return mapper.map(payment, PaymentDTO.class);
	}

	@Override
	public void delete(Long id) {
		try {
			Payment payment = paymentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Resource not found, Payment id = " + id));
			paymentRepository.delete(payment);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceClosedException("Resouce not found, Payment id = " + id);
		} catch (DatabaseException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

}
