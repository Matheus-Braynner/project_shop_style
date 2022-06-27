/*package com.compass.shopstyle.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.compass.portalcompass"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(appInfo())
				.globalOperationParameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                    .name("Authorization")
                                    .description("Header for Token JWT")
                                    .modelRef(new ModelRef("string"))
                                    .parameterType("header")
                                    .required(false)
                                    .build()));
	}
	
	private ApiInfo appInfo() {
		return new ApiInfoBuilder()
				.title("Api Rest Compass")
				.description("Project-Shop-Style"
						+ "\n Dev"
						+ "\n Matheus , https://github.com/Matheus-Braynner")
				.version("0.0.1")
				.license("Apache Licence Version 3.0")
				.contact(new Contact("Repository","https://github.com/Matheus-Braynner/project_shop_style",""))
				.build();
	}
}
*/
