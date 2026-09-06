package com.taller.ms_productos.exception;

public class StockInsuficienteException extends RuntimeException {

    public StockInsuficienteException(Integer stock, int cantidad) {
        super("No se puede descontar " + cantidad + " unidades: el stock disponible es " + stock);
    }
}