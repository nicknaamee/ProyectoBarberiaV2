package com.BloqueNico.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BloqueNico.dto.ResenaDTO;
import com.BloqueNico.model.Barbero;
import com.BloqueNico.model.Cliente;
import com.BloqueNico.model.Resena;
import com.BloqueNico.repository.BarberoRepository;
import com.BloqueNico.repository.ClienteRepository;
import com.BloqueNico.repository.ResenaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarberoRepository barberoRepository;

    public List<Resena> traerTodasLasResenas() {
        log.info("Consultando todas las reseñas registradas en el sistema");
        return resenaRepository.findAll();
    }

    public Resena traerResenaPorId(Long id) {
        log.info("Buscando reseña con ID: {}", id);
        return resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la reseña con el ID: " + id));
    }

    public Resena guardarNuevaResena(ResenaDTO datos) {
        log.info("Guardando nueva reseña del cliente ID: {} para el barbero ID: {}", 
        datos.getIdDelClienteQueOpina(), datos.getIdDelBarberoEvaluado());
        
        Cliente cliente = clienteRepository.findById(datos.getIdDelClienteQueOpina())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        
        Barbero barbero = barberoRepository.findById(datos.getIdDelBarberoEvaluado())
                .orElseThrow(() -> new RuntimeException("Barbero no encontrado"));

        Resena nuevaResena = new Resena();
        nuevaResena.setCliente(cliente);
        nuevaResena.setBarbero(barbero);
        nuevaResena.setCalificacionDeEstrellas(datos.getCalificacionDeEstrellas());
        nuevaResena.setComentarioDelCliente(datos.getComentarioDelCliente());
        nuevaResena.setFechaDeLaResena(datos.getFechaDeLaResena() != null ?
        datos.getFechaDeLaResena() : LocalDate.now());
        
        return resenaRepository.save(nuevaResena);
    }

    public void eliminarResena(Long id) {
        log.info("Eliminando reseña con ID: {}", id);
        traerResenaPorId(id);
        resenaRepository.deleteById(id);
    }

    public ResenaDTO convertirAResenaDTO(Resena resena) {
        return ResenaDTO.builder()
                .idDeLaResena(resena.getIdDeLaResena())

                .idDelClienteQueOpina(resena.getCliente().getIdCliente())
                .idDelBarberoEvaluado(resena.getBarbero().getIdBarbero())
                .calificacionDeEstrellas(resena.getCalificacionDeEstrellas())
                .comentarioDelCliente(resena.getComentarioDelCliente())
                .fechaDeLaResena(resena.getFechaDeLaResena())
                .build();
    }
}