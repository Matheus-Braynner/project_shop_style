package com.compass.order.services;

import java.util.List;

import com.compass.order.dto.OrderDTO;
import com.compass.order.dto.OrderFormDTO;

public interface OrderService {
	
	OrderDTO insert(OrderFormDTO orderObj);
	
	List<OrderDTO> findAll();
	
	List<OrderDTO> findByCustomerId(Long id);

}
