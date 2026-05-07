package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Table(name = "sucursales") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Sucursal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeLaSucursal;

    private String nombreDeLaSucursal;
    private String direccionCompleta;
    private String telefonoDeLaSucursal;
    private Boolean estaAbierta;
}
