package com.compass.mscatalog.config.connections;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compass.mscatalog.config.connections.entity.SkuOrder;
import com.compass.mscatalog.entities.Sku;
import com.compass.mscatalog.services.SkuService;
import com.compass.mscatalog.services.exception.FailToConvertToObject;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitMQConsumer {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SkuService skuService;

	public SkuOrder toObject(final String message, final Class<SkuOrder> clazz) {
		try {
			return objectMapper.readValue(message, clazz);
		} catch (final Exception exception) {
			throw new FailToConvertToObject("Fail to convert to object");
		}
	}

	@RabbitListener(queues = "${mq.queues.sku-order}")
	public void processMessage(String skuOrder) {
		SkuOrder skuOrder2Object = toObject(skuOrder, SkuOrder.class);
		for (Sku sku : skuOrder2Object.getSku()) {
			skuService.updateOrderSku(sku.getId(), sku.getQuantity());
		}
	}
}
