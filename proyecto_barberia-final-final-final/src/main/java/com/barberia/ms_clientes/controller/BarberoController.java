package com.barberia.ms_clientes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barberia.ms_clientes.dto.BarberoDTO;
import com.barberia.ms_clientes.model.Barbero;
import com.barberia.ms_clientes.service.BarberoService;

@RestController
@RequestMapping("/api/barberos")
public class BarberoController {

    @Autowired
    private BarberoService barberoService;

    @GetMapping
    public List<BarberoDTO> getBarberos() {
        return barberoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public BarberoDTO buscarPorId(@PathVariable Long id) {
        return barberoService.buscarPorId(id);
    }

    @PostMapping
    public Barbero crearBarbero(@RequestBody Barbero barbero) {
        return barberoService.guardarBarbero(barbero);
    }

    @PutMapping("/{id}")
    public Barbero actualizar(@PathVariable Long id, @RequestBody Barbero barbero) {
        return barberoService.actualizarBarberos(id, barbero);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        return barberoService.eliminar(id);
    }

    @GetMapping("/especialidad/{esp}")
    public List<BarberoDTO> buscarPorEspecialidad(@PathVariable String esp) {
        return barberoService.buscarPorEspecialidadBarbero(esp);
    }

    @GetMapping("/estado/{status}")
    public List<BarberoDTO> buscarPorEstado(@PathVariable boolean status) {
        return barberoService.buscarPorEstado(status);
    }
}