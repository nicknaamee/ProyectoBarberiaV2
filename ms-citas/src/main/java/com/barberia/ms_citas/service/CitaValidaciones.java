package com.barberia.ms_citas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.barberia.ms_citas.dto.CitaDTO;
import com.barberia.ms_citas.dto.BarberoExternoDTO;
import com.barberia.ms_citas.model.Cita;

import reactor.core.publisher.Mono;

@Service
public class CitaValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Boolean validarNullVacio(Cita cita) {
        if (cita.getIdBarbero() == null || cita.getIdCliente() == null) {
            return false;
        }
        if (cita.getFechaCita() == null || cita.getHoraInicio() == null) {
            return false;
        }
        return true;
    }

    public BarberoExternoDTO obtenerBarbero(Long id) {
        BarberoExternoDTO barberoRecuperado = new BarberoExternoDTO();
        try {
            BarberoExternoDTO resultado = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/api/v1/barberos/" + id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(BarberoExternoDTO.class)
                .block();

            if (resultado != null) {
                return resultado;
            }
            barberoRecuperado.setIdBarbero(id);
            barberoRecuperado.setNombreBarbero("Sin barbero asignado");
            return barberoRecuperado;

        } catch (Exception e) {
            barberoRecuperado.setIdBarbero(id);
            barberoRecuperado.setNombreBarbero("Desconectado del servicio de barberos (offline o no existe)");
            return barberoRecuperado;
        }
    }

    public CitaDTO convertirADTO(Cita cita) {
        CitaDTO dto = new CitaDTO();
        dto.setIdCita(cita.getIdCita());
        dto.setFechaCita(cita.getFechaCita());
        dto.setHoraInicio(cita.getHoraInicio());
        dto.setEstadoCita(cita.getEstadoCita());
        dto.setBarbero(obtenerBarbero(cita.getIdBarbero()));
        return dto;
    }
}
