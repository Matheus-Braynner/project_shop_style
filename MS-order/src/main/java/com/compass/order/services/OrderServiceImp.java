package com.compass.order.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.order.dto.OrderDTO;
import com.compass.order.dto.OrderFormDTO;
import com.compass.order.entities.Order;
import com.compass.order.enums.Status;
import com.compass.order.feignclients.CatalogClient;
import com.compass.order.feignclients.CustomerClient;
import com.compass.order.feignclients.PaymentClient;
import com.compass.order.feignclients.response.Address;
import com.compass.order.feignclients.response.Customer;
import com.compass.order.feignclients.response.Installment;
import com.compass.order.feignclients.response.Payment;
import com.compass.order.feignclients.response.Sku;
import com.compass.order.repositories.OrderRepository;

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
	
	@Override
	public OrderDTO insert(OrderFormDTO orderObj) {
		Order order = new Order();
		Customer customer = customerClient.getCustomer(orderObj.getCustomer().getId());
		Address address = customerClient.getAddress(orderObj.getCustomer().getAddressId());
		Payment payment = paymentClient.getPayment(orderObj.getPayment().getId());
		Installment installment = new Installment();
		installment.setAmount(null);
		installment.setPayment(payment);
		Double total = 0.0;
		List<Sku> cart = new ArrayList<>();
		for(Sku sku : orderObj.getCart()) {
			Sku skuImp = catalogClient.getSku(sku.getId());
			skuImp.setQuantity(sku.getQuantity());
			cart.add(skuImp);
			total += (skuImp.getPrice() * sku.getQuantity());
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
		return mapper.map(order, OrderDTO.class);
	}

	@Override
	public List<OrderDTO> findAll() {
		return null;
	}

	@Override
	public List<OrderDTO> findByCustomerId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
