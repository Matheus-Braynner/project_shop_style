package com.compass.msaudit.services;

import com.compass.msaudit.dto.OrderDTO;
import com.compass.msaudit.entities.Order;

public interface AuditService {
	
	OrderDTO save(Order order);
	
	OrderDTO findById(String id);

}
