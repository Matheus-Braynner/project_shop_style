package com.compass.order.config.connections;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.compass.order.config.connections.entity.SkuOrder;

@Component
public class RabbitMQProducer {
	private static final String EXCHANGE_NAME = "amq.direct";
	
	@Autowired
    private AmqpTemplate amqpTemplate;
	
	@Autowired
    private AmqpAdmin amqpAdmin;

	private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }
    
	private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding relation(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }
    
    @Value("${mq.queues.sku-order}")
    private String sku_order;

    public void adds(SkuOrder skuOrder) {
        Queue skuQueue = this.queue(sku_order);
        
        String routingKey = skuOrder.getOrderId();
        
        amqpTemplate.convertAndSend(EXCHANGE_NAME, routingKey, skuOrder);

        DirectExchange exchange = this.directExchange();

        Binding relationOrder = this.relation(skuQueue, exchange);

        this.amqpAdmin.declareQueue(skuQueue);

        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(relationOrder);
        
        System.out.println("Send msg = " + skuOrder);
    }
    
//    String routingKey = skuOrder.getOrderId();
//    amqpTemplate.convertAndSend(EXCHANGE_NAME, routingKey, skuOrder);
//    System.out.println("Send msg = " + skuOrder);

}
