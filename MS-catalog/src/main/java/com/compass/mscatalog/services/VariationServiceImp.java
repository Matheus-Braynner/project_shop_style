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
	
	@Autowired
	private SequenceGeneratorService sequenceService;

	@Override
	public VariationDTO insert(VariationFormDTO variationObj) {
		variationObj.setId(sequenceService.getSequenceNumber(Product.SEQUENCE_NAME));
		Variation variation = variationRepository.insert(mapper.map(variationObj, Variation.class));
	}
}
