package com.compass.payment.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.payment.dto.InstallmentDTO;
import com.compass.payment.dto.InstallmentFormDTO;
import com.compass.payment.services.InstallmentService;

@RestController
@RequestMapping(value = "/v1/installments")
public class InstallmentController {

	@Autowired
	private InstallmentService installmentService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<InstallmentDTO> insert(@RequestBody @Valid InstallmentFormDTO installmentBody) {
		InstallmentDTO installment = installmentService.insert(installmentBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(installment);
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<InstallmentDTO> update(@PathVariable Long id, @RequestBody @Valid InstallmentFormDTO installmentBody) {
		InstallmentDTO installment = installmentService.update(id, installmentBody);
		return ResponseEntity.ok(installment);
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		installmentService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
