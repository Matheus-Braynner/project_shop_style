package com.compass.mscatalog.services;

import com.compass.mscatalog.dto.SkuDTO;
import com.compass.mscatalog.dto.SkuFormDTO;
import com.compass.mscatalog.entities.Sku;

public interface SkuService {

	SkuDTO insert(SkuFormDTO skuObj);
	
	SkuDTO update(Long id, SkuFormDTO skuObj);
	
	SkuDTO findById(Long id);
	
	void delete(Long id);
	
	Sku updateOrderSku(Long id, Integer quantity);
}
