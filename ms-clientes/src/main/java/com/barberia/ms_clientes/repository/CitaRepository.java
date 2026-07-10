package com.barberia.ms_clientes.repository;

import com.barberia.ms_clientes.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByIdDelBarberoAsignado(Long idDelBarbero);

    List<Cita> findByIdDelClienteQueAgenda(Long idDelCliente);

    List<Cita> findByIdDelBarberoAsignadoAndFechaDeLaCita(Long idDelBarbero, LocalDate fecha);

    List<Cita> findByEstadoDeLaCita(String estado);

}
