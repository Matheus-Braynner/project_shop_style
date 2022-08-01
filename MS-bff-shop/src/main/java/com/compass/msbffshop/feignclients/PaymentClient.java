package com.compass.msbffshop.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.compass.msbffshop.feignclients.request.payment.PaymentDTO;


@Component
@FeignClient("MS-payment")
public interface PaymentClient {

	
	@GetMapping(value = "/v1/payments")
	List<PaymentDTO> findAllPayments();
}
