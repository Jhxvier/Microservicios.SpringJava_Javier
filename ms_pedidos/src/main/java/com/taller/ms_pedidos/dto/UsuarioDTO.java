package com.taller.ms_pedidos.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String email;
    private String ciudad;

}