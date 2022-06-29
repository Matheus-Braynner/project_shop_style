package com.compass.order.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.compass.order.feignclients.request.AddressDTO;
import com.compass.order.feignclients.request.CustomerDTO;

@Component
@FeignClient("customer")
public interface CustomerClient {

	@GetMapping(value = "/v1/customers/{id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id);
	
	@GetMapping(value = "/v1/addresses/{id}")
	public ResponseEntity<AddressDTO> getAddress(@PathVariable Long id);
}
