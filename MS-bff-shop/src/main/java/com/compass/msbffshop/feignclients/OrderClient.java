package com.compass.msbffshop.feignclients;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.compass.msbffshop.feignclients.request.enums.Status;
import com.compass.msbffshop.feignclients.request.order.OrderDTO;
import com.compass.msbffshop.feignclients.request.order.OrderFormDTO;

@Component
@FeignClient("MS-order")
public interface OrderClient {

	@PostMapping(value = "/v1/orders")
	OrderDTO insertOrder (@RequestBody @Valid OrderFormDTO orderBody);
	
	@GetMapping(value = "/v1/orders/customers/{id}")
	List<OrderDTO> findOrderByCustomerId(@PathVariable Long id, @RequestParam(required = false) Date startDate, 
			@RequestParam(required = false) Date endDate, @RequestParam(required = false) Status status);
	
}
