package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;

// esta tabla guarda el resumen de citas de cada cliente
@Entity
@Table(name = "citas_resumen")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Citas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCliente;
    private Integer totalCitas;
}
