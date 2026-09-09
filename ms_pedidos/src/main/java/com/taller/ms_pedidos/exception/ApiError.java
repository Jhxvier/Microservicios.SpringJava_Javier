package com.taller.ms_pedidos.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiError {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String error;
    private int status;
    private String mensaje;
    private List<String> detalles;

    public ApiError(int status, String error, String mensaje, List<String> detalles) {
        this.status = status;
        this.error = error;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }
}