package com.compass.order.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.compass.order.feignclients.response.Sku;

@Component
@FeignClient("catalog")
public interface CatalogClient {

	@GetMapping(value = "/v1/skus/{id}")
	Sku getSku(@PathVariable Long id);
}
