package com.taller.ms_usuarios.common.exceptions;

public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(Long id) {
        super("No se encontró el ID: "+id);
    }
}
