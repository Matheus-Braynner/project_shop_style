package com.compass.order.config.connections.entity;

import java.io.Serializable;
import java.util.List;

import com.compass.order.feignclients.response.Sku;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SkuOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String orderId;
	private List<Sku> sku;

}
