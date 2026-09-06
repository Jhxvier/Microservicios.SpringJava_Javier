package com.taller.ms_productos.validator;

import com.taller.ms_productos.exception.NombreDuplicadoException;
import com.taller.ms_productos.exception.ProductoConStockException;
import com.taller.ms_productos.exception.StockInsuficienteException;
import com.taller.ms_productos.model.Producto;
import com.taller.ms_productos.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

//REGLAS DE NEGOCIO
@Component
@Data
@AllArgsConstructor
public class ProductoValidator {

    //inyección de dependencias
    private final ProductoRepository productoRepository;

    //REGLA 1: no pueden existir dos productos con el mismo nombre (ignorando mayúsculas/minúsculas)
    public void checkNombreUnicoCreate(String nombre) {

        if (productoRepository.existsByNombreIgnoreCase(nombre)) {
            throw new NombreDuplicadoException(nombre);
        }

    }

    //Validar nombre a la hora de actualizar
    public void checkNombreUnicoUpdate(Producto productoActual, String nombre) {
        boolean cambiarNombre = !productoActual.getNombre().equalsIgnoreCase(nombre);
        if (cambiarNombre && productoRepository.existsByNombreIgnoreCase(nombre)) {
            throw new NombreDuplicadoException(nombre);
        }
    }

    //REGLA 2: no se puede descontar más stock del que hay disponible
    public void checkStockSuficiente(Integer stock, int cantidad) {
        if (cantidad > stock) {
            throw new StockInsuficienteException(stock, cantidad);
        }
    }

    //REGLA 3: no se puede eliminar un producto que todavía tiene stock (stock > 0)
    public void checkProductoConStock(Producto producto) {
        if (producto.getStock() > 0) {
            throw new ProductoConStockException(producto.getNombre());
        }
    }

}