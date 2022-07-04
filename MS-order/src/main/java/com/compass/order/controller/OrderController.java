package com.compass.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.order.dto.OrderDTO;
import com.compass.order.dto.OrderFormDTO;
import com.compass.order.services.OrderService;

@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<OrderDTO> insert (@RequestBody OrderFormDTO orderBody) {
		OrderDTO order = orderService.insert(orderBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}

}
