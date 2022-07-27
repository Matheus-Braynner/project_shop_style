package com.compass.msclaudit.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.msclaudit.feignclients.OrderClient;
import com.compass.msclaudit.feignclients.requests.OrderDTO;
import com.compass.msclaudit.feignclients.requests.OrderFormDTO;
import com.compass.msclaudit.feignclients.response.Order;
import com.compass.msclaudit.repositories.AuditRepository;

@Service
public class AuditServiceImp implements AuditService {

	@Autowired
	private OrderClient orderClient;
	
	@Autowired
	private AuditRepository auditRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public OrderDTO insert(OrderFormDTO order) {
		Order orderFeign = orderClient.insert(order);
		Order orderSaved = auditRepository.save(orderFeign);
		return mapper.map(orderSaved, OrderDTO.class);
	}

	@Override
	public OrderDTO findById(String id) {
		Order orderFeignById = orderClient.findById(id);
		return mapper.map(orderFeignById, OrderDTO.class);
	}
	
	
	
}
