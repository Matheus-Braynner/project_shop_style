package com.compass.order.feignclients.request;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SkuDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long skuId;
	private Integer quantity;
	
	
}
