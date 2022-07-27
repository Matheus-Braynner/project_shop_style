package com.compass.order.config.connections;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compass.order.config.connections.entity.PaymentStatus;
import com.compass.order.services.OrderServiceImp;
import com.compass.order.services.exceptions.FailToConvertToObject;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitMQConsumer {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private OrderServiceImp orderServiceImp;
	
	public PaymentStatus toObject(final String message, final Class<PaymentStatus> clazz) {
		try {
			return objectMapper.readValue(message, clazz);
		} catch (final Exception exception) {
			throw new FailToConvertToObject("Fail to convert to object");
		}
	}
	
	@RabbitListener(queues = "${mq.queues.sku-order}")
	public void processMessage(String paymentStatus) {
		PaymentStatus paymentOrder2Object = toObject(paymentStatus, PaymentStatus.class);
		orderServiceImp.builderStatusPayment(paymentOrder2Object);
	}
}
