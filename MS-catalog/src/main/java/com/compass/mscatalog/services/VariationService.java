package com.compass.mscatalog.services;

import com.compass.mscatalog.dto.VariationDTO;
import com.compass.mscatalog.dto.VariationFormDTO;

public interface VariationService {

	VariationDTO insert(VariationFormDTO variationObj);
}
