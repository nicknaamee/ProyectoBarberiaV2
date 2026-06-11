package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.ServicioDTO;
import com.barberia.ms_clientes.model.Servicio;
import com.barberia.ms_clientes.repository.ServicioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> traerTodosLosServicios() {
        log.info("Consultando la lista completa de servicios en la base de datos"); 
        return servicioRepository.findAll();
    }

    public Servicio traerServicioPorId(Long idDelServicio) {
        log.info("Buscando servicio con ID: {}", idDelServicio); 
        return servicioRepository.findById(idDelServicio)
            .orElseThrow(() -> new RuntimeException(
                "Error: No se encontró el servicio con el ID: " + idDelServicio));}


    public Servicio guardarNuevoServicio(ServicioDTO datos) {
        log.info("Registrando nuevo servicio: {}", datos.getNombreDelServicio()); 
        
        Servicio nuevoServicio = new Servicio();
        nuevoServicio.setNombreDelServicio(datos.getNombreDelServicio());
        nuevoServicio.setPrecioDelServicio(datos.getPrecioDelServicio());
        nuevoServicio.setDuracionEnMinutos(datos.getDuracionEnMinutos());
        
        return servicioRepository.save(nuevoServicio); 
    }

    public Servicio actualizarServicio(Long idDelServicio, ServicioDTO datosNuevos) {
        log.info("Iniciando actualización del servicio con ID: {}", idDelServicio);
        
        Servicio servicioExistente = traerServicioPorId(idDelServicio);
        
        servicioExistente.setNombreDelServicio(datosNuevos.getNombreDelServicio());
        servicioExistente.setPrecioDelServicio(datosNuevos.getPrecioDelServicio());
        servicioExistente.setDuracionEnMinutos(datosNuevos.getDuracionEnMinutos());
        
        return servicioRepository.save(servicioExistente);
    }

    public void eliminarServicio(Long idDelServicio) {
        log.info("Intentando eliminar servicio con ID: {}", idDelServicio);
        traerServicioPorId(idDelServicio);
        servicioRepository.deleteById(idDelServicio);
        log.info("Servicio con ID {} eliminado exitosamente", idDelServicio); 
    }


    public ServicioDTO convertirAServicioDTO(Servicio servicio) {
        return ServicioDTO.builder()
                .idDelServicio(servicio.getIdDelServicio())
                .nombreDelServicio(servicio.getNombreDelServicio())
                .precioDelServicio(servicio.getPrecioDelServicio())
                .duracionEnMinutos(servicio.getDuracionEnMinutos())
                .build();
    }
}