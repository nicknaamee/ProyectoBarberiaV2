package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Table(name = "proveedores") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Proveedor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelProveedor;

    private String nombreDelProveedor;
    private String emailDeContacto;
    private String telefonoDeContacto;
    private String productoQueNosProvee;
}
