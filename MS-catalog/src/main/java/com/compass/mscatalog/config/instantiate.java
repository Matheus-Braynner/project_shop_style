package com.compass.mscatalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.compass.mscatalog.services.DbService;

@Configuration
public class instantiate {

	@Autowired
	private DbService dbService;
	
	@Bean
	public Boolean instantiateDatabase() {
		dbService.instantiateDatabase();
		return true;
	}
}
