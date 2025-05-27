package com.example.DestroyableToy;

import com.example.DestroyableToy.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DestroyableToyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DestroyableToyApplication.class, args );
	}
	@Bean
	public CommandLineRunner loadMockProducts(ProductService productService) {
		return args -> productService.insertMockData();
	}
}
