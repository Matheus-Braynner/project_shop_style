package com.compass.order.services;

import java.util.Date;
import java.util.List;

import com.compass.order.dto.OrderDTO;
import com.compass.order.dto.OrderFormDTO;
import com.compass.order.enums.Status;

public interface OrderService {
	
	OrderDTO insert(OrderFormDTO orderObj);
	
	List<OrderDTO> findAll();
	
	OrderDTO findById(String id);
	
	List<OrderDTO> findByCustomerId(Long id, Date startDate, Date endDate, Status status);

}
