package com.compass.order.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.compass.order.feignclients.response.Address;
import com.compass.order.feignclients.response.Customer;

@Component
@FeignClient("MS-customer")
public interface CustomerClient {

	@GetMapping(value = "/v1/customers/{id}")
	Customer findByIdCustomer(@PathVariable Long id);
	
	@GetMapping(value = "/v1/addresses/{id}")
	Address findByIdAddress(@PathVariable Long id);
}
