package com.filmreserve.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"com.filmreserve.api.Controllers",
							   "com.filmreserve.api.Services",
							   "com.filmreserve.api.Dao",
							   "com.filmreserve.api.Models",
							   "com.filmreserve.Configuration"})
public class ApiApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("Authorization");
			}
		};
	}
}
