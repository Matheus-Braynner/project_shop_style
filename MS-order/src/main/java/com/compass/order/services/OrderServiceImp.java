package com.compass.order.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.compass.order.config.connections.entity.SkuOrder;
import com.compass.order.dto.OrderDTO;
import com.compass.order.dto.OrderFormDTO;
import com.compass.order.entities.Order;
import com.compass.order.enums.Status;
import com.compass.order.feignclients.CatalogClient;
import com.compass.order.feignclients.CustomerClient;
import com.compass.order.feignclients.PaymentClient;
import com.compass.order.feignclients.request.SkuDTO;
import com.compass.order.feignclients.response.Address;
import com.compass.order.feignclients.response.Customer;
import com.compass.order.feignclients.response.Installment;
import com.compass.order.feignclients.response.Payment;
import com.compass.order.feignclients.response.Sku;
import com.compass.order.repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderServiceImp implements OrderService {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CatalogClient catalogClient;
	
	@Autowired
	private CustomerClient customerClient;
	
	@Autowired
	private PaymentClient paymentClient;
	
	@Autowired
	private OrderRepository orderRepository;
		
	@Value("${mq.queues.sku-order}")
	private String sku_order;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public OrderDTO insert(OrderFormDTO orderObj) {
		Order order = new Order();
		Customer customer = customerClient.findByIdCustomer(orderObj.getCustomer().getId());
		Address address = customerClient.findByIdAddress(orderObj.getCustomer().getAddressId());
		Payment payment = paymentClient.getPayment(orderObj.getPayment().getId());
		Installment installment = new Installment();
		installment.setAmount(orderObj.getPayment().getInstallments());
		installment.setPayment(payment);
		installment.setBrand(null);
		Double total = 0.0;
		List<Sku> cart = new ArrayList<>();
		for(SkuDTO skuDTO : orderObj.getCart()) {
			Sku skuImp = catalogClient.getSku(skuDTO.getSkuId());
			skuImp.setQuantity(skuDTO.getQuantity());
			cart.add(skuImp);
			total += (skuImp.getPrice() * skuDTO.getQuantity());
		}
		
		order.setCustomer(customer);
		order.setAddress(address);
		order.setPayment(payment);
		order.setInstallment(installment);
		order.setCart(cart);
		order.setDate(new Date());
		order.setStatus(Status.PROCESSING_PAYMENT);
		order.setTotal(total);
		orderRepository.save(order);
		String object2String = writeValueAsStringSkuOrder(builderSku(order));
		rabbitTemplate.convertAndSend(sku_order, object2String);
		return mapper.map(order, OrderDTO.class);
	}

	@Override
	public List<OrderDTO> findAll() {
		List<Order> list = orderRepository.findAll();
		List<OrderDTO> listDTO = list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
		return listDTO;
	}

	@Override
	public List<OrderDTO> findByCustomerId(Long id, Date startDate, Date endDate, Status status) {
		Stream<Order> ordersStream  = orderRepository.findByCustomerId(id).stream();
		
		if(status != null) {
			ordersStream = ordersStream.filter(x -> (x.getStatus() == status));
		}
		
		if(startDate != null) {
			ordersStream = ordersStream.filter(x -> (x.getDate().before(startDate) || x.getDate().equals(startDate)));
		}
		
		if(endDate != null) {
			ordersStream = ordersStream.filter(x -> (x.getDate().before(endDate) || x.getDate().equals(endDate)));
		}
		
		return ordersStream.map(OrderDTO::new).collect(Collectors.toList());
	}
	
	public SkuOrder builderSku(Order order) {
		SkuOrder skuOrder = new SkuOrder();
		skuOrder.setOrderId(order.getId());
		skuOrder.setSku(order.getCart());
		return skuOrder;
	}
	
	   private String writeValueAsStringSkuOrder (SkuOrder object) {
	        try {
	            return objectMapper.writeValueAsString(object);
	        } catch (JsonProcessingException e) {
	            throw new RuntimeException(e);
	        }
	    }
	
}
