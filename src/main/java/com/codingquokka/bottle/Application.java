package com.codingquokka.bottle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@ImportResource({"classpath:databaseContext.xml", "classpath:mailContext.xml"})
//@EnableWebMvc
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
