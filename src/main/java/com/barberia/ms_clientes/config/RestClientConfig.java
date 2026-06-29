package com.barberia.ms_clientes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

    public static final String MS_BARBEROS_URL = "http://localhost:8082/api/v1";
    public static final String MS_PRODUCTOS_URL = "http://localhost:8083/api/v1";

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
