package com.compass.msclaudit.config.connections;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compass.msclaudit.feignclients.requests.OrderFormDTO;
import com.compass.msclaudit.feignclients.response.Order;
import com.compass.msclaudit.services.AuditService;
import com.compass.msclaudit.services.exceptions.FailToConvertToObject;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitMQConsumer {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private ModelMapper mapper;
	
	public Order toObject(final String message, final Class<Order> clazz) {
		try {
			return objectMapper.readValue(message, clazz);
		} catch (final Exception exception) {
			throw new FailToConvertToObject("Fail to convert to object");
		}
	}
	
	@RabbitListener(queues = "${mq.queues.order-audit}")
	public void processMessage(String orderStatus) {
		Order paymentOrder2Object = toObject(orderStatus, Order.class);
		OrderFormDTO converOrderToForm = mapper.map(paymentOrder2Object, OrderFormDTO.class);
		auditService.insert(converOrderToForm);
	}
}
