package com.compass.mscatalog.config.connections.entity;

import java.io.Serializable;
import java.util.List;

import com.compass.mscatalog.entities.Sku;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class SkuOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String orderId;
	private List<Sku> sku;

}
