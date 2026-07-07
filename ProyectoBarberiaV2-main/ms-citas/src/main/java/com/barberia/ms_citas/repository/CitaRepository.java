package com.barberia.ms_citas.repository;

import com.barberia.ms_citas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {


    List<Cita> findByIdBarbero(Long idBarbero);


    List<Cita> findByIdCliente(Long idCliente);

    List<Cita> findByEstadoCita(String estado);

}
