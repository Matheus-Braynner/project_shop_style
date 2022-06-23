package com.compass.mscatalog.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.mscatalog.dto.VariationDTO;
import com.compass.mscatalog.dto.VariationFormDTO;
import com.compass.mscatalog.entities.Product;
import com.compass.mscatalog.entities.Variation;
import com.compass.mscatalog.repositories.VariationRepository;

@Service
public class VariationServiceImp implements VariationService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private VariationRepository variationRepository;
	

	@Override
	public VariationDTO insert(VariationFormDTO variationObj) {
		Variation variation = variationRepository.insert(mapper.map(variationObj, Variation.class));
		return mapper.map(variation, VariationDTO.class);
	}
}
