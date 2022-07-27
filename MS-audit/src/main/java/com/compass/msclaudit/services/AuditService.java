package com.compass.msclaudit.services;

import com.compass.msclaudit.feignclients.requests.OrderDTO;
import com.compass.msclaudit.feignclients.requests.OrderFormDTO;

public interface AuditService {
	
	OrderDTO insert(OrderFormDTO order);
	
	OrderDTO findById(String id);

}
