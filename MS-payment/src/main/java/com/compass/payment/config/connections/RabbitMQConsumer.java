package com.compass.payment.config.connections;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compass.payment.config.connections.entities.PaymentOrder;
import com.compass.payment.services.PaymentServiceImp;
import com.compass.payment.services.exceptions.FailToConvertToObject;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitMQConsumer {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private PaymentServiceImp paymentServiceImp;
	
	public PaymentOrder toObject(final String message, final Class<PaymentOrder> clazz) {
		try {
			return objectMapper.readValue(message, clazz);
		} catch (Exception e) {
			throw new FailToConvertToObject("Fail to convert to object");
		}
	}
	
	@RabbitListener(queues = "${mq.queues.payment-order}")
	public void processMessage(String paymentOrder) {
		PaymentOrder paymentOrder2Object = toObject(paymentOrder, PaymentOrder.class);
		paymentServiceImp.statusMessageRabbit(paymentOrder2Object);
	}
}
