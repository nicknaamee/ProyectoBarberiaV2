package com.barberia.ms_clientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.barberia.ms_clientes.dto.BarberoExternoDTO;
import com.barberia.ms_clientes.dto.ClienteDTO;
import com.barberia.ms_clientes.dto.FacturaDTO;
import com.barberia.ms_clientes.dto.ServicioExternoDTO;
import com.barberia.ms_clientes.model.Factura;

import reactor.core.publisher.Mono;

@Service
public class FacturaValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

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
            barberoRecuperado.setNombreBarbero("Desconectado (barbero offline)");
            return barberoRecuperado;
        }
    }

    public ClienteDTO obtenerCliente(Long id) {
        ClienteDTO clienteRecuperado = new ClienteDTO();
        try {
            // Asumiendo puerto 8081 para el microservicio de Clientes
            ClienteDTO resultado = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/v1/clientes/" + id) 
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(ClienteDTO.class)
                .block();

            if (resultado != null) {
                return resultado;
            }
            clienteRecuperado.setIdCliente(id);
            clienteRecuperado.setNombreCliente("Sin cliente asignado");
            return clienteRecuperado;
        } catch (Exception e) {
            clienteRecuperado.setIdCliente(id);
            clienteRecuperado.setNombreCliente("Desconectado (cliente offline)");
            return clienteRecuperado;
        }
    }

    public ServicioExternoDTO obtenerServicio(Long id) {
        ServicioExternoDTO servicioRecuperado = new ServicioExternoDTO();
        try {
            // Asumiendo puerto 8083 para el microservicio de Servicios
            ServicioExternoDTO resultado = webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/api/v1/servicios/" + id) 
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(ServicioExternoDTO.class)
                .block();

            if (resultado != null) {
                return resultado;
            }
            servicioRecuperado.setIdDelServicio(id);
            servicioRecuperado.setPrecioDelServicio(0.0);
            return servicioRecuperado;
        } catch (Exception e) {
            servicioRecuperado.setIdDelServicio(id);
            servicioRecuperado.setPrecioDelServicio(0.0);
            return servicioRecuperado;
        }
    }

    public FacturaDTO convertirADTO(Factura factura) {
        FacturaDTO dto = new FacturaDTO();
        dto.setIdFactura(factura.getIdFactura());
        dto.setIdCita(factura.getCita().getIdCita());
        dto.setMontoTotal(factura.getMontoTotal());
        dto.setMetodoDePago(factura.getMetodoDePago());
        dto.setFechaEmision(factura.getFechaEmision());
        
        // Obtener nombres haciendo llamadas HTTP
        ClienteDTO cliente = obtenerCliente(factura.getCita().getIdCliente());
        BarberoExternoDTO barbero = obtenerBarbero(factura.getCita().getIdBarbero());
        
        dto.setNombreCliente(cliente.getNombreCliente());
        dto.setNombreBarbero(barbero.getNombreBarbero());
        
        return dto;
    }
}
