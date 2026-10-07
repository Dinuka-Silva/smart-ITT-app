package com.smartitt.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI smartIttOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Smart ITT API")
                        .description("Intelligent Inter Terminal Transportation Management System REST API")
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Auth"))
                .components(new Components().addSecuritySchemes("Bearer Auth",
                        new SecurityScheme()
                                .name("Bearer Auth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
