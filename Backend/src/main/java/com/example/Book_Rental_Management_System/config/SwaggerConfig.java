package com.example.Book_Rental_Management_System.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bookRentalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Book Rental Management System API")
                        .description("API documentation for managing books and rentals")
                        .version("1.0"));
    }
}

