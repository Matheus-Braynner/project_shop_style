package com.compass.msbffshop.feignclients;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.compass.msbffshop.feignclients.request.customer.AddressDTO;
import com.compass.msbffshop.feignclients.request.customer.AddressFormDTO;
import com.compass.msbffshop.feignclients.request.customer.ChangePasswordDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerFormDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerLoginFormDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerNewFormDTO;
import com.compass.msbffshop.feignclients.response.CustomerLogin;

@Component
@FeignClient("MS-customer")
public interface CustomerClient {
	
	@PostMapping(value = "/v1/customers/login")
	CustomerDTO login(@Valid @RequestBody CustomerLoginFormDTO customerLoginBody);
	
	@PutMapping(value = "/v1/customers/login/{id}")
	CustomerDTO changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordDTO changePasswordBody);
	
	@PostMapping(value = "/v1/customers")
	CustomerDTO insert(@Valid @RequestBody CustomerFormDTO customerBody);
	
	@GetMapping(value = "/v1/customers/{id}")
	CustomerDTO findById(@PathVariable Long id);
	
	@PutMapping(value = "/v1/customers/{id}")
	CustomerDTO update(@PathVariable Long id, @Valid @RequestBody CustomerNewFormDTO customerBody);
	
	@PostMapping(value = "/v1/addresses")	
	AddressDTO insert(@Valid @RequestBody AddressFormDTO addressBody);
	
	@PutMapping(value = "/v1/addresses/{id}")
	AddressDTO update(@PathVariable Long id, @Valid @RequestBody AddressFormDTO addressBody);
	
	@DeleteMapping(value = "/v1/addresses/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id);
	
	@GetMapping(value = "/v1/customers")
	CustomerLogin findByEmail(@RequestParam(required = true) String email);
	
}
