package com.compass.msaudit.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.msaudit.dto.OrderDTO;
import com.compass.msaudit.entities.Order;
import com.compass.msaudit.repositories.AuditRepository;
import com.compass.msaudit.services.exceptions.ResourceNotFoundException;

@Service
public class AuditServiceImp implements AuditService {

	@Autowired
	private AuditRepository auditRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public OrderDTO save(Order order) {
		Order orderForm = auditRepository.save(order);
		return mapper.map(orderForm, OrderDTO.class);
	}

	@Override
	public OrderDTO findById(String id) {
		Order orderFindById = auditRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found, id = " + id));
		return mapper.map(orderFindById, OrderDTO.class);
	}

	
	
	
}
