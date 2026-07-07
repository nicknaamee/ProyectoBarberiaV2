package com.barberia.ms_clientes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

// config de swagger pa documentar la API
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
            .title("Api administracion de barberia")
            .version("1.1")
            .description("Con esta API se puede administrar una barberia, incluyendo clientes, barberos, citas, servicios, productos, facturas, reseñas y mas.")
        );
    }

}
