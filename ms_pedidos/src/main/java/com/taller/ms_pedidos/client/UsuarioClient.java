package com.taller.ms_pedidos.client;

import com.taller.ms_pedidos.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class UsuarioClient {

    private final RestClient usuarioRestClient;

    //GET a ms-usuarios: me devuelve el usuario o propaga su 404
    public UsuarioDTO obtenerUsuario(Long id) {
        return usuarioRestClient.get()
                .uri("/api/usuarios/{id}", id)
                .retrieve()
                .body(UsuarioDTO.class);
    }

}