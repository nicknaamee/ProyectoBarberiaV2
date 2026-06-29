package com.BloqueNico.proyectoBarberia.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.BloqueNico.proyectoBarberia.model.Resena;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResenaValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Boolean validarResena(Resena resena) {
        if (resena.getCalificacionDeEstrellas() == null || resena.getComentarioDelCliente() == null) {
            return false;
        }
        if (resena.getCalificacionDeEstrellas() < 1 || resena.getCalificacionDeEstrellas() > 5) {
            return false;
        }
        if (resena.getComentarioDelCliente().trim().isEmpty() || resena.getComentarioDelCliente().length() > 500) {
            return false;
        }
        if (resena.getFechaDeLaResena() != null && resena.getFechaDeLaResena().isAfter(LocalDate.now())) {
            return false; 
        }

        WebClient client = webClientBuilder.build();

        try {
            client.get()
                .uri("http://proyectoBarberia/api/v1/clientes/" + resena.getCliente().getIdCliente())
                .retrieve()
                .toBodilessEntity()
                .block(); 
        } catch (WebClientResponseException.NotFound e) {
            log.warn("Cliente con ID {} no encontrado en el microservicio remoto", resena.getCliente().getIdCliente());
            return false;
        } catch (Exception e) {
            log.error("Error al validar cliente remoto: {}", e.getMessage());
            return false; 
        }

        try {
            client.get()
                .uri("http://proyectoBarberia/api/barberos/" + resena.getBarbero().getIdBarbero())
                .retrieve()
                .toBodilessEntity()
                .block();
        } catch (WebClientResponseException.NotFound e) {
            log.warn("Barbero con ID {} no encontrado en el microservicio remoto", resena.getBarbero().getIdBarbero());
            return false;
        } catch (Exception e) {
            log.error("Error al validar barbero remoto: {}", e.getMessage());
            return false; 
        }

        return true;
    }
}