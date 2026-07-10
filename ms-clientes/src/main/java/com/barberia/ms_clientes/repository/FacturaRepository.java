package com.barberia.ms_clientes.repository;

import com.barberia.ms_clientes.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    Optional<Factura> findByIdDeLaCitaFacturada(Long idDeLaCita);

    List<Factura> findByMetodoDePago(String metodoDePago);

    List<Factura> findByFechaYHoraDeEmisionBetween(LocalDateTime inicio, LocalDateTime fin);

    boolean existsByIdDeLaCitaFacturada(Long idDeLaCita);
}
