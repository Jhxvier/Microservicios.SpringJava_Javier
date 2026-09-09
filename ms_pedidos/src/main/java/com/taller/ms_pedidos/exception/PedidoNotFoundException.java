package com.taller.ms_pedidos.exception;

public class PedidoNotFoundException extends RuntimeException {

    public PedidoNotFoundException(Long id) {
        super("No se encontró el pedido con id: " + id);
    }
}