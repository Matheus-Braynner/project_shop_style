package com.compass.msbffshop.feignclients;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.compass.msbffshop.feignclients.request.customer.ChangePasswordDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerFormDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerLoginFormDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerNewFormDTO;

@Component
@FeignClient("MS-customer")
public interface CustomerClient {
	
	@PostMapping(value = "/v1/customers/login")
	CustomerDTO login(@Valid @RequestBody CustomerLoginFormDTO customerLoginBody);
	
	@PutMapping(value = "/login/{id}")
	CustomerDTO changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordDTO changePasswordBody);
	
	@PostMapping(value = "/v1/customers")
	CustomerDTO insert(@Valid @RequestBody CustomerFormDTO customerBody);
	
	@GetMapping(value = "/v1/customers/{id}")
	CustomerDTO findById(@PathVariable Long id);
	
	@PutMapping(value = "/v1/customers/{id}")
	CustomerDTO update(@PathVariable Long id, @Valid @RequestBody CustomerNewFormDTO customerBody);
	
}
