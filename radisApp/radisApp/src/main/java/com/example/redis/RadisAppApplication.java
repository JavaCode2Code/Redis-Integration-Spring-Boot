package com.example.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.redis")
@EnableCaching
public class RadisAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RadisAppApplication.class, args);
	}

}
