package com.compass.order.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.compass.order.feignclients.response.Payment;

@Component
@FeignClient("MS-payment")
public interface PaymentClient {
	
	@GetMapping(value = "/v1/payments/{id}") 
	Payment getPayment(@PathVariable Long id);

}
