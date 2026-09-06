package com.taller.ms_usuarios.common.exceptions;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super("Ya existe este email: "+email);
    }
}
