package com.BloqueNico.proyectoBarberia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del producto debe tener entre 3 y 100 caracteres")
    @Column(name = "nombre_del_producto", nullable = false, length = 100)
    private String nombreDelProducto;

    @NotNull(message = "La cantidad en stock es obligatoria")
    @Min(value = 0, message = "La cantidad en stock no puede ser un número negativo")
    @Column(name = "cantidad_en_stock", nullable = false)
    private Integer cantidadEnStock;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario del producto debe ser un valor mayor a cero")
    @Column(name = "precio_unitario_del_producto", nullable = false)
    private Double precioUnitarioDelProducto;
}