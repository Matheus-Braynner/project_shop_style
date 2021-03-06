package com.compass.order.config.connections;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQProducer {
	private static final String EXCHANGE_NAME = "amq.direct";
	
	@Autowired
    private AmqpAdmin amqpAdmin;
	
	@Value("${mq.queues.sku-order}")
	private String sku_order;
	 
	@Value("${mq.queues.payment-order}")
	private String payment_order;
	
	@Value("${mq.queues.order-audit}")
	private String audit_order;

	
	private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }
    
	private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding relation(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }
    
    @PostConstruct
    public void adds() {
        Queue skuQueue = this.queue(sku_order);
        Queue paymentQueue = this.queue(payment_order);
        Queue auditQueue = this.queue(audit_order);
        
        DirectExchange exchange = this.directExchange();

        Binding relationSku = this.relation(skuQueue, exchange);
        Binding relationPayment = this.relation(paymentQueue, exchange);
        Binding relationAudit = this.relation(auditQueue, exchange);

        this.amqpAdmin.declareQueue(skuQueue);
        this.amqpAdmin.declareQueue(paymentQueue);
        this.amqpAdmin.declareQueue(auditQueue);

        this.amqpAdmin.declareExchange(exchange);
       
        this.amqpAdmin.declareBinding(relationSku);
        this.amqpAdmin.declareBinding(relationPayment);
        this.amqpAdmin.declareBinding(relationAudit);
    }
    
}
