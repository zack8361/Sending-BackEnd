package com.codingquokka.bottle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@Configuration
@ImportResource({"classpath:databaseContext.xml"})
public class Application {
	//"classpath:mailContext.xml"
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
