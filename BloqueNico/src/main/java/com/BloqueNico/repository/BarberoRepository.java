package com.BloqueNico.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BloqueNico.model.Barbero;

@Repository
public interface BarberoRepository extends JpaRepository<Barbero, Long> {

    List<Barbero> findByEstado(Boolean estado);

    List<Barbero> findByEspecialidadBarbero(String especialidad);
}