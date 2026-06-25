package com.BloqueNico.proyectoBarberia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProyectoBarberiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoBarberiaApplication.class, args);
    }
}