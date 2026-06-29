package com.barberia.ms_clientes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// modelo de producto pa la venta en la barberia (shampoo, cera, etc)
@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto; // id del producto

    private String nombreDelProducto; // nombre del producto
    private Integer cantidadEnStock; // cuantos hay
    private Double precioUnitarioDelProducto; // precio por unidad
}
