package com.compass.order;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableRabbit
public class MsOrderApplication {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MsOrderApplication.class, args);
	}

}
