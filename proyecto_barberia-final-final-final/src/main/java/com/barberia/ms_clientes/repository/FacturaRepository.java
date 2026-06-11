package com.barberia.ms_clientes.repository;

import com.barberia.ms_clientes.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    Optional<Factura> findByCitaIdCita(Long idCita);

}