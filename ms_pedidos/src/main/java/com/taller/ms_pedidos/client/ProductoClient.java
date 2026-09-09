package com.taller.ms_pedidos.client;

import com.taller.ms_pedidos.dto.ProductoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class ProductoClient {

    private final RestClient productoRestClient;

    //GET a ms-productos: me devuelve el producto
    public ProductoDTO obtenerProducto(Long id) {
        return productoRestClient.get()
                .uri("/api/productos/{id}", id)
                .retrieve()
                .body(ProductoDTO.class);
    }

    //PATCH a ms-productos: descuenta stock (si no hay, ms-productos responde 409)
    public void descontarStock(Long productoId, int cantidad) {
        productoRestClient.patch()
                .uri("/api/productos/{id}/descontar-stock?cantidad={cantidad}", productoId, cantidad)
                .retrieve()
                .toBodilessEntity();
    }

    //PATCH a ms-productos: repone stock (accion de compensacion)
    public void reponerStock(Long productoId, int cantidad) {
        productoRestClient.patch()
                .uri("/api/productos/{id}/reponer-stock?cantidad={cantidad}", productoId, cantidad)
                .retrieve()
                .toBodilessEntity();
    }

}