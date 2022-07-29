package com.compass.msbffshop.feignclients.request.catalog;

import java.io.Serializable;
import java.util.List;

import com.compass.msbffshop.feignclients.request.enums.Size;
import com.compass.msbffshop.feignclients.response.Media;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SkuDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Double price;
	private Integer quantity;
	private String color;
	private Size size;
	private Integer height;
	private Integer width;
	private List<Media> images;
	
}
