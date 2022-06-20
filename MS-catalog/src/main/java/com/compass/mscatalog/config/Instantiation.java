package com.compass.mscatalog.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.compass.mscatalog.entities.Product;
import com.compass.mscatalog.repositories.ProductRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		productRepository.deleteAll();
		
		Product p1 = new Product(1L, "Camisa", "Camisa do Santa Cruz", true);
		Product p2 = new Product(2L, "Camisa", "Camisa do Flamengo", true);
		Product p3 = new Product(3L, "Camisa", "Camisa do Cruzeiro", true);
		Product p4 = new Product(4L, "Short", "Short do Santa Cruz", true);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		
	}

}
