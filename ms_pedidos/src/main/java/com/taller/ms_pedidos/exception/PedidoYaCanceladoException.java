package com.taller.ms_pedidos.exception;

public class PedidoYaCanceladoException extends RuntimeException {

    public PedidoYaCanceladoException(Long id) {
        super("El pedido con id: " + id + " ya esta cancelado");
    }
}