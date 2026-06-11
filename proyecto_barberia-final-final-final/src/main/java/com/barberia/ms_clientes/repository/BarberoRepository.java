package com.barberia.ms_clientes.repository;

import com.barberia.ms_clientes.model.Barbero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarberoRepository extends JpaRepository<Barbero, Long> {

    List<Barbero> findByEstado(Boolean estado);

    List<Barbero> findByEspecialidadBarbero(String especialidad);
}