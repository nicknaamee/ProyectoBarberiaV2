package com.barberia.ms_barberos.repository;

import com.barberia.ms_barberos.model.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiciosRepository extends JpaRepository<Servicios, Long> {

}
