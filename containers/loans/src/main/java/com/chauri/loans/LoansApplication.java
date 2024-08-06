package com.chauri.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loan Micro service REST API Documentation",
				description = "Microservice project Loan Microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Aditya Chaurasia",
						email = "filler-email@example.com",
						url = "https://github.com/chaurasia-aditya"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/chaurasia-aditya"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Microservice project Loan microservice REST API Documentation",
				url = "https://github.com/chaurasia-aditya"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
