package com.compass.mscatalog.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.compass.mscatalog.dto.SkuDTO;
import com.compass.mscatalog.dto.SkuFormDTO;
import com.compass.mscatalog.dto.SkuFormUpdateDTO;
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

		Sku skuTeste = fromSkuFormDTO(skuObj);
		//skuTeste.setImages(mediasSaved);
		 
		Sku save = skuRepository.save(skuTeste);
		
		List<Media> medias = new ArrayList<>();
		for(String img : skuObj.getImages()) {
			Media media = new Media();
			media.setImageUrl(img);
			media.setSku(save);
			medias.add(media);
			
		}
		List<Media> mediasSaved = mediaRepository.saveAll(medias);
		
		
		return mapper.map(save, SkuDTO.class);
	}

	@Override
	public SkuDTO update(Long id, SkuFormUpdateDTO skuObj) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found, id = " + skuObj.getProductId()));
		Sku sku = skuRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found, id = " + skuObj.getId()));
		List<Media> images = sku.getImages();
		sku.setPrice(skuObj.getPrice());
		sku.setQuantity(skuObj.getQuantity());
		sku.setColor(skuObj.getColor());
		sku.setSize(skuObj.getSize());
		sku.setHeight(skuObj.getHeight());
		sku.setWidth(skuObj.getWidth());
		sku.setImages(skuObj.getImages());
		sku.setProductId(product);
		images.forEach(x -> System.out.println(x));
		return mapper.map(sku, SkuDTO.class);
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
	
	private Sku fromSkuFormDTO(SkuFormDTO skuObj) {
		Sku sku = new Sku();
		sku.setPrice(skuObj.getPrice());
		sku.setQuantity(skuObj.getQuantity());
		sku.setColor(skuObj.getColor());
		sku.setSize(skuObj.getSize());
		sku.setHeight(skuObj.getHeight());
		sku.setWidth(skuObj.getWidth());
		return sku;
	}

}
