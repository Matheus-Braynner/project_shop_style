package com.compass.msaudit.config.connections;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compass.msaudit.entities.Order;
import com.compass.msaudit.services.AuditService;
import com.compass.msaudit.services.exceptions.FailToConvertToObject;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitMQConsumer {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private AuditService auditService;
	
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
		auditService.save(paymentOrder2Object);
	}
}
