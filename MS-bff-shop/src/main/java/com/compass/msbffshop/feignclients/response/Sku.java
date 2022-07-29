package com.compass.msbffshop.feignclients.response;

import java.io.Serializable;

import com.compass.msbffshop.feignclients.request.enums.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sku implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Double price;
	private Integer quantity;
	private String color;
	private Size size;
	private Integer height;
	private Integer width;
	

}
