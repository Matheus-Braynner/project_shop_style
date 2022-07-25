package com.compass.payment.config.connections;

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
	 
	@Value("${mq.queues.payment-order}")
	private String payment_order;

	
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
        Queue paymentQueue = this.queue(payment_order);
        
        DirectExchange exchange = this.directExchange();

        Binding relationPayment = this.relation(paymentQueue, exchange);

        this.amqpAdmin.declareQueue(paymentQueue);

        this.amqpAdmin.declareExchange(exchange);
       
        this.amqpAdmin.declareBinding(relationPayment);
    }
    
}
