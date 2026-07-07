package com.BloqueNico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
            .title("API de Clientes - Barbería")
            .version("1.0")
            .description("API encargada de la gestión de clientes dentro del sistema de la barbería.")
        );
    }
}
