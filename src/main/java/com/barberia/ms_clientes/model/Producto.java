package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;
@Entity 
@Table(name = "productos") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Producto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelProducto;

    private String nombreDelProducto;
    private Integer cantidadEnStock;
    private Double precioUnitarioDelProducto;
}
