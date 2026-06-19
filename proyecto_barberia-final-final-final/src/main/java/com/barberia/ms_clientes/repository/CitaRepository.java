package com.barberia.ms_clientes.repository;

import com.barberia.ms_clientes.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {


    List<Cita> findByBarbero(Integer idBarbero);


    List<Cita> findByCliente(Integer idCliente);

    List<Cita> findByEstadoCita(String estado);

}