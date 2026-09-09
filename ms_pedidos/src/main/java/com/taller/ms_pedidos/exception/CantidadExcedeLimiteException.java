package com.taller.ms_pedidos.exception;

public class CantidadExcedeLimiteException extends RuntimeException {

    public CantidadExcedeLimiteException(int maxCantidad) {
        super("La cantidad no puede superar " + maxCantidad + " unidades por pedido");
    }
}