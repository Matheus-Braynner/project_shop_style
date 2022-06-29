package com.compass.mscatalog.services;

import com.compass.mscatalog.dto.SkuDTO;
import com.compass.mscatalog.dto.SkuFormDTO;

public interface SkuService {

	SkuDTO insert(SkuFormDTO skuObj);
	
	SkuDTO update(Long id, SkuFormDTO skuObj);
	
	SkuDTO findById(Long id);
	
	void delete(Long id);
}
