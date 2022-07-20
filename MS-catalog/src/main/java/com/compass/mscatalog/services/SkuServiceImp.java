package com.compass.mscatalog.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.compass.mscatalog.dto.SkuDTO;
import com.compass.mscatalog.dto.SkuFormDTO;
import com.compass.mscatalog.entities.Media;
import com.compass.mscatalog.entities.Product;
import com.compass.mscatalog.entities.Sku;
import com.compass.mscatalog.repositories.MediaRepository;
import com.compass.mscatalog.repositories.ProductRepository;
import com.compass.mscatalog.repositories.SkuRepository;
import com.compass.mscatalog.services.exception.DatabaseException;
import com.compass.mscatalog.services.exception.ResourceNotFoundException;

@Service
public class SkuServiceImp implements SkuService {

	@Autowired
	private SkuRepository skuRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MediaRepository mediaRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public SkuDTO insert(SkuFormDTO skuObj) {

		Product product = productRepository.findById(skuObj.getProductId())
			.orElseThrow(() -> new ResourceNotFoundException("Resource not found, ID = " + skuObj.getProductId()));
		Sku sku = new Sku();
		sku.setProductId(product);
		sku.setColor(skuObj.getColor());
		sku.setPrice(skuObj.getPrice());
		sku.setQuantity(skuObj.getQuantity());
		sku.setSize(skuObj.getSize());	
		sku.setHeight(skuObj.getHeight());
		sku.setWidth(skuObj.getWidth());

		for (String imgUrl : skuObj.getImages()) {
			Media media = new Media(imgUrl, sku);
			sku.addImages(media);
			mediaRepository.save(media);
		}
		Sku skuSaved = skuRepository.save(mapper.map(sku, Sku.class));

		return mapper.map(skuSaved, SkuDTO.class);
	}
	
	@Override
	public SkuDTO findById(Long id) {
		Sku sku = skuRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(null));
		return mapper.map(sku, SkuDTO.class);
	}

	@Override
	public SkuDTO update(Long id, SkuFormDTO skuObj) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found, Product id = " + skuObj.getProductId()));
		Sku sku = skuRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found, id = " + id));
		sku.setProductId(product);
		sku.setPrice(skuObj.getPrice());
		sku.setQuantity(skuObj.getQuantity());
		sku.setColor(skuObj.getColor());
		sku.setSize(skuObj.getSize());
		sku.setHeight(skuObj.getHeight());
		sku.setWidth(skuObj.getWidth());
		

		for (String imgUrl : skuObj.getImages()) {
			mediaRepository.save(new Media(imgUrl, sku));
		}

		Sku skuSaved = skuRepository.save(mapper.map(sku, Sku.class));
		return mapper.map(skuSaved, SkuDTO.class);
	}

	@Override
	public void delete(Long id) {
		try {
			Sku sku = skuRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Object not found, id : " + id));
			skuRepository.delete(sku);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Resource not found " + id);
		} catch (DatabaseException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public Sku updateOrderSku(Long id, Integer quantity) {
		Sku sku = skuRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found, Sku ID = " + id));
		sku.setQuantity(sku.getQuantity() - quantity);
		return skuRepository.save(sku);
	}

	

}
