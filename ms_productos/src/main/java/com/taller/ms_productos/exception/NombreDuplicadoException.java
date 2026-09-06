package com.taller.ms_productos.exception;

public class NombreDuplicadoException extends RuntimeException {

    public NombreDuplicadoException(String nombre) {
        super("Ya existe un producto con el nombre: " + nombre);
    }
}