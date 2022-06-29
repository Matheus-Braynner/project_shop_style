package com.compass.order.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.compass.order.feignclients.request.SkuDTO;

@Component
@FeignClient("catalog")
public interface CatalogClient {

	@GetMapping(value = "/v1/skus/{id}")
	public ResponseEntity<SkuDTO> findById(@PathVariable Long id);
}
