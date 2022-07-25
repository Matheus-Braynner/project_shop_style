package com.compass.order.controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compass.order.dto.OrderDTO;
import com.compass.order.dto.OrderFormDTO;
import com.compass.order.enums.Status;
import com.compass.order.services.OrderService;

@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<OrderDTO> insert (@RequestBody @Valid OrderFormDTO orderBody) {
		OrderDTO order = orderService.insert(orderBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll() {
		List<OrderDTO> listOrder = orderService.findAll();
		return ResponseEntity.ok().body(listOrder);
	}
	
	@GetMapping(value = "/customers/{id}")
	public ResponseEntity<List<OrderDTO>> findByCustomerId(@PathVariable Long id, @RequestParam(required = false) Date startDate, 
			@RequestParam(required = false) Date endDate, @RequestParam(required = false) Status status){
		List<OrderDTO> listByCustomer = orderService.findByCustomerId(id, startDate, endDate, status);
		return ResponseEntity.ok().body(listByCustomer);
	}
}
