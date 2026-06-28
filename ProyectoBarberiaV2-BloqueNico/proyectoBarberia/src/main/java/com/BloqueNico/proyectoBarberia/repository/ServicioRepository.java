package com.BloqueNico.proyectoBarberia.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BloqueNico.proyectoBarberia.model.Servicio;



@Repository
public interface ServicioRepository extends JpaRepository <Servicio, Long> {

}
