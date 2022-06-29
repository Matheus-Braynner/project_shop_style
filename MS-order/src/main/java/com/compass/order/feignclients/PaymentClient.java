package com.compass.order.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.compass.order.feignclients.request.PaymentDTO;

@Component
@FeignClient("payment")
public interface PaymentClient {
	
	@GetMapping(value = "v1/payments/{id}") 
	public ResponseEntity<PaymentDTO> findById(@PathVariable Long id);

}
