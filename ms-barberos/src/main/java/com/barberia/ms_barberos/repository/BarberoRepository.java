package com.barberia.ms_barberos.repository;

import com.barberia.ms_barberos.model.Barbero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarberoRepository extends JpaRepository<Barbero, Long> {

    List<Barbero> findByEstaActivoTrue();

    List<Barbero> findByEspecialidadDelBarbero(String especialidad);
}