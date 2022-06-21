package com.compass.shopstyle;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
public class ShopStyleApplication {

	
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ShopStyleApplication.class, args);
	
	
	}

}
