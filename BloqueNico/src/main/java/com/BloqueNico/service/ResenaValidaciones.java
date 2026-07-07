package com.BloqueNico.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.BloqueNico.model.Resena;

@Service
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

        try {
            webClientBuilder.build().get()
                    .uri("http://localhost:8081/api/v1/clientes/" + resena.getCliente().getIdCliente())
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (Exception e) {
            return false;
        }
        try {
            webClientBuilder.build().get()
                    .uri("http://localhost:8081/api/v1/barberos/" + resena.getBarbero().getIdBarbero())
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}