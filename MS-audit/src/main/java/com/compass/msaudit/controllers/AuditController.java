package com.compass.msaudit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.msaudit.dto.OrderDTO;
import com.compass.msaudit.services.AuditService;

@RestController
@RequestMapping(value = "/v1/audit/orders")
public class AuditController {
	
	@Autowired
	private AuditService auditService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderDTO> findById(@PathVariable String id) {
		OrderDTO order = auditService.findById(id);
		return ResponseEntity.ok().body(order);
	}

}
