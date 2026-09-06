package com.taller.ms_productos.exception;

public class ProductoConStockException extends RuntimeException {

    public ProductoConStockException(String nombre) {
        super("No se puede eliminar el producto '" + nombre + "' porque aún tiene stock");
    }
}