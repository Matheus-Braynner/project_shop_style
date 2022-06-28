package com.compass.mscatalog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.mscatalog.dto.SkuDTO;
import com.compass.mscatalog.dto.SkuFormDTO;
import com.compass.mscatalog.services.SkuService;

@RestController
@RequestMapping(value = "/v1/skus")
public class SkuController {
	
	@Autowired
	private SkuService skuService;

	@PostMapping()
	@Transactional
	public ResponseEntity<SkuDTO> insert(@RequestBody SkuFormDTO skuBody) {
		SkuDTO sku = skuService.insert(skuBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(sku);
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<SkuDTO> update(@PathVariable Long id, @RequestBody SkuFormDTO skuBody) {
		SkuDTO sku = skuService.update(id, skuBody);
		return ResponseEntity.ok(sku);
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		skuService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
