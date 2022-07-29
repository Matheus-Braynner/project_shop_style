package com.compass.msbffshop.feignclients.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String imageUrl;
	private Sku sku;
	
	public Media(String imgUrl, Sku sku) {
		this.imageUrl = imgUrl;
		this.sku = sku;
	}

}
