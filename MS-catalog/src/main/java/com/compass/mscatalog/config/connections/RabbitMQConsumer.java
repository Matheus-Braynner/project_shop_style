package com.compass.mscatalog.config.connections;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.compass.mscatalog.config.connections.entity.Sku;
import com.compass.mscatalog.config.connections.entity.SkuOrder;
import com.compass.mscatalog.services.SkuService;

@Component
public class RabbitMQConsumer {

	@Autowired
	private SkuService skuService;
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@RabbitListener(queues = "${mq.queues.sku-order}")
	public void processMessage(SkuOrder skuOrder) {
		for (Sku sku : skuOrder.getSku()) {
			skuService.updateOrderSku(sku.getId(), sku.getQuantity());
		}
		System.out.println(skuOrder);
	}
}
