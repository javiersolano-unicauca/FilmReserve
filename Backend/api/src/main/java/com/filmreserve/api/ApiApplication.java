package com.filmreserve.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.filmreserve.api.Controllers",
							   "com.filmreserve.api.Services",
							   "com.filmreserve.api.Dao",
							   "com.filmreserve.api.Models"})
public class ApiApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApiApplication.class, args);
	}

}
