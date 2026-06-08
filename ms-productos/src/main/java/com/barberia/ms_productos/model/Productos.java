package com.barberia.ms_productos.model;

import jakarta.persistence.*;
import lombok.*;

// esta tabla guarda que productos tiene cada sucursal
@Entity
@Table(name = "productos_sucursal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idSucursal;
    private Long idProducto;
    private Integer stock;
}
