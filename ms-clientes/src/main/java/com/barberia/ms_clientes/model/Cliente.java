package com.barberia.ms_clientes.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelCliente;
    private String nombreDelCliente;
    private String apellidoDelCliente;

    @Column(unique = true)
    private String emailDelCliente;
    
    private String telefonoDelCliente;
    private LocalDate fechaRegistro;

}
