package com.compass.payment.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.payment.dto.PaymentDTO;
import com.compass.payment.dto.PaymentFormDTO;
import com.compass.payment.services.PaymentService;

@RestController
@RequestMapping(value = "/v1/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PaymentDTO> insert(@RequestBody @Valid PaymentFormDTO paymentBody) {
		PaymentDTO payment = paymentService.insert(paymentBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(payment);
	}
	
	@GetMapping
	public ResponseEntity<List<PaymentDTO>> findAll() {
		List<PaymentDTO> paymentList = paymentService.findAll();
		return ResponseEntity.ok().body(paymentList);
	}
	
	@GetMapping(value = "/{id}") 
	public ResponseEntity<PaymentDTO> findById(@PathVariable Long id) {
		PaymentDTO payment = paymentService.findById(id);
		return ResponseEntity.ok().body(payment);
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		paymentService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
