package com.barberia.ms_barberos.controller;

import com.barberia.ms_barberos.dto.EmpleadoDTO;
import com.barberia.ms_barberos.model.Empleado;
import com.barberia.ms_barberos.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService servicio;

    @GetMapping
    public List<Empleado> traerTodos() {
        return servicio.traerTodos();
    }

    @GetMapping("/{id}")
    public Empleado traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Empleado crear(@Valid @RequestBody EmpleadoDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @Valid @RequestBody EmpleadoDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
