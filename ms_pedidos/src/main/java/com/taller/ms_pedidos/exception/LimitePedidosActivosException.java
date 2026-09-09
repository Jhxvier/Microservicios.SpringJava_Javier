package com.taller.ms_pedidos.exception;

public class LimitePedidosActivosException extends RuntimeException {

    public LimitePedidosActivosException(int maxActivos) {
        super("El usuario ya tiene " + maxActivos + " pedidos activos, no puede crear mas");
    }
}