package com.compass.msclaudit.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.msclaudit.feignclients.requests.OrderDTO;
import com.compass.msclaudit.feignclients.requests.OrderFormDTO;
import com.compass.msclaudit.services.AuditService;

@RestController
@RequestMapping(value = "/v1/audit/orders")
public class AuditController {
	
	@Autowired
	private AuditService auditService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<OrderDTO> insert(@RequestBody @Valid OrderFormDTO orderBody) {
		OrderDTO order = auditService.insert(orderBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderDTO> findById(@PathVariable String id) {
		OrderDTO order = auditService.findById(id);
		return ResponseEntity.ok().body(order);
	}

}
