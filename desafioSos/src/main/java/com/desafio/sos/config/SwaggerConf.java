package com.desafio.sos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConf {

	@Bean
	public Docket sosTec() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.desafio.sos.restcontroller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaIfo());
	}

	private ApiInfo metaIfo() {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		ApiInfo info = new ApiInfo("Desafio SOS TECNOLOGIA",
				"API REST", "7.7.7",
				"termsOfServiceUrl",
				"educhianelli@gmail.com", 
				"license", "licenseUrl");
		return info;
	}
}
