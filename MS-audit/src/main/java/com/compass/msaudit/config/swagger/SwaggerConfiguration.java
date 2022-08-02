package com.compass.msaudit.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.compass.msaudit"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(appInfo());
	}

	private ApiInfo appInfo() {
		return new ApiInfoBuilder()
				.title("Api Rest Shop-style")
				.description("Fast Track"
						+ "\n Development team"
						+ "\n Matheus Braynner\n" 
						+ "Github - https://github.com/Matheus-Braynner\n" 
						+ "Linkedin - https://www.linkedin.com/in/matheus-braynner-939b85200/")
				.version("0.0.1")
				.license("Apache Licence Version 3.0")
				.contact(new Contact("Repository","https://github.com/Matheus-Braynner/project_shop_style",""))
				.build();
	}
}

