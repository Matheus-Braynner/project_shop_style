package com.compass.msclaudit.feignclients;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.compass.msclaudit.feignclients.requests.OrderFormDTO;
import com.compass.msclaudit.feignclients.response.Order;

@Component
@FeignClient("MS-order")
public interface OrderClient {

	
	@PostMapping(value = "/v1/orders")
	Order insert (@RequestBody @Valid OrderFormDTO orderBody);
	
	@GetMapping(value = "/v1/orders/{id}")
	Order findById(@PathVariable String id);
	
}
