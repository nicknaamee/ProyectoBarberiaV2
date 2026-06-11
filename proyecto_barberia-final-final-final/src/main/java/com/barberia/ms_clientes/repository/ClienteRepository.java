package com.barberia.ms_clientes.repository;

import com.barberia.ms_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> { 

    Optional<Cliente> findByEmailCliente(String email);
    
    boolean existsByEmailCliente(String email);

    Optional<Cliente> findByTelefonoCliente(String telefono);
    
}
