package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Table(name = "barberos") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Barbero {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelBarbero;

    private String nombreDelBarbero;
    private String especialidadDelBarbero;
    private Boolean estaActivo;             

}
