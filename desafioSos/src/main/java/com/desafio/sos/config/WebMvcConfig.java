package com.desafio.sos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig {

	public void addCorsMappings(CorsRegistry registry) {
	/*	registry.addMapping("/**").allowedOrigins(url)
				.allowedMethods(GET.name(), POST.name(), HEAD.name(), OPTIONS.name(), PUT.name())
				.allowCredentials(true);*/
	}
}