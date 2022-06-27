package com.compass.mscatalog.services;

import com.compass.mscatalog.dto.SkuDTO;
import com.compass.mscatalog.dto.SkuFormDTO;
import com.compass.mscatalog.dto.SkuFormUpdateDTO;

public interface SkuService {

	SkuDTO insert(SkuFormDTO skuObj);
	
	SkuDTO update(Long id, SkuFormUpdateDTO skuObj);
	
	void delete(Long id);
}
