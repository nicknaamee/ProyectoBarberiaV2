package com.BloqueNico.proyectoBarberia.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {
    private Long idDelProducto;
    private String nombreDelProducto;
    private Integer cantidadEnStock;
    private Double precioUnitarioDelProducto;
}
