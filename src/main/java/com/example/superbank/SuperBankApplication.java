package com.example.superbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.superbank"})
@EnableJpaRepositories(basePackages = "com.example.superbank.repositories")
public class SuperBankApplication {
	public static void main(String[] args) {
		SpringApplication.run(SuperBankApplication.class, args);
    }
}
