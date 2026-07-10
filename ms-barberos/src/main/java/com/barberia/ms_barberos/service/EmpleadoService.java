package com.barberia.ms_barberos.service;

import com.barberia.ms_barberos.dto.EmpleadoDTO;
import com.barberia.ms_barberos.model.Empleado;
import com.barberia.ms_barberos.repository.EmpleadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository repo;

    public List<Empleado> traerTodos() {
        log.info("buscando todos los empleados");
        return repo.findAll();
    }

    public Empleado traerPorId(Long id) {
        log.info("buscando empleado con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el empleado con id: " + id));
    }

    public Empleado guardar(EmpleadoDTO datos) {
        log.info("guardando nuevo empleado: {}", datos.getNombreCompleto());
        Empleado nuevo = new Empleado();
        nuevo.setNombreCompleto(datos.getNombreCompleto());
        nuevo.setCargoEnLaBarberia(datos.getCargoEnLaBarberia());
        nuevo.setSalarioMensual(datos.getSalarioMensual());
        nuevo.setFechaDeContrato(datos.getFechaDeContrato());
        return repo.save(nuevo);
    }

    public Empleado actualizar(Long id, EmpleadoDTO datos) {
        log.info("actualizando empleado con id: {}", id);
        Empleado empleado = traerPorId(id);
        empleado.setNombreCompleto(datos.getNombreCompleto());
        empleado.setCargoEnLaBarberia(datos.getCargoEnLaBarberia());
        empleado.setSalarioMensual(datos.getSalarioMensual());
        empleado.setFechaDeContrato(datos.getFechaDeContrato());
        return repo.save(empleado);
    }

    public void eliminar(Long id) {
        log.info("eliminando empleado con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
