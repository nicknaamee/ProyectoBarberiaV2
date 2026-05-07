package com.barberia.ms_clientes.repository;

import com.barberia.ms_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository 
public interface ClienteRepository extends JpaRepository<Cliente, Long> { // Paso 2: Extender JPA

    Optional<Cliente> findByEmailDelCliente(String email);
    
    boolean existsByEmailDelCliente(String email);

    Optional<Cliente> findByTelefonoDelCliente(String telefono);
    
}
