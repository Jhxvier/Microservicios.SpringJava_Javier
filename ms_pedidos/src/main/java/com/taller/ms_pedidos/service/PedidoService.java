package com.taller.ms_pedidos.service;

import com.taller.ms_pedidos.client.ProductoClient;
import com.taller.ms_pedidos.client.UsuarioClient;
import com.taller.ms_pedidos.dto.PedidoRequestDTO;
import com.taller.ms_pedidos.dto.PedidoResponseDTO;
import com.taller.ms_pedidos.dto.ProductoDTO;
import com.taller.ms_pedidos.dto.UsuarioDTO;
import com.taller.ms_pedidos.exception.PedidoNotFoundException;
import com.taller.ms_pedidos.mapper.PedidoMapper;
import com.taller.ms_pedidos.enums.EstadoPedido;
import com.taller.ms_pedidos.model.Pedido;
import com.taller.ms_pedidos.repository.PedidoRepository;
import com.taller.ms_pedidos.validator.PedidoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final PedidoValidator pedidoValidator;
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;

    public PedidoResponseDTO crearPedido(PedidoRequestDTO request) {

        //reglas de negocio internas
        pedidoValidator.checkCantidadMaxima(request.getCantidad());
        pedidoValidator.checkLimitePedidosActivos(request.getUsuarioId());

        //le pedimos el usuario a ms-usuarios (si no existe, propaga el 404)
        UsuarioDTO usuario = usuarioClient.obtenerUsuario(request.getUsuarioId());

        //le pedimos el producto a ms-productos
        ProductoDTO producto = productoClient.obtenerProducto(request.getProductoId());

        //calcular el total (precio * cantidad)
        BigDecimal total = producto.getPrecio().multiply(BigDecimal.valueOf(request.getCantidad()));

        //pedimos a ms-productos que descuente el stock
        productoClient.descontarStock(request.getProductoId(), request.getCantidad());

        //ahora guardamos el pedido
        Pedido pedido = pedidoMapper.toEntity(request);
        pedido.setTotal(total);
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado(EstadoPedido.CONFIRMADO);
        pedido = pedidoRepository.save(pedido);

        //respuesta combinada: pedido + usuario + producto
        return pedidoMapper.toResponse(pedido, usuario, producto);
    }

    //GET todos: datos crudos del pedido, sin enriquecer (sin llamar a otros microservicios)
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    //GET por id: detalle enriquecido con datos del usuario y del producto
    public PedidoResponseDTO obtenerDetalle(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));

        UsuarioDTO usuario = usuarioClient.obtenerUsuario(pedido.getUsuarioId());
        ProductoDTO producto = productoClient.obtenerProducto(pedido.getProductoId());

        return pedidoMapper.toResponse(pedido, usuario, producto);
    }

    public PedidoResponseDTO cancelarPedido(Long id) {
        //buscar el pedido (404 si no existe)
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));

        //validar que no este ya cancelado
        pedidoValidator.checkPedidoNoCancelado(pedido);

        //accion de compensacion: reponer el stock en ms-productos
        productoClient.reponerStock(pedido.getProductoId(), pedido.getCantidad());

        //cambiamos el estado y guardamos
        pedido.setEstado(EstadoPedido.CANCELADO);
        pedido = pedidoRepository.save(pedido);

        UsuarioDTO usuario = usuarioClient.obtenerUsuario(pedido.getUsuarioId());
        ProductoDTO producto = productoClient.obtenerProducto(pedido.getProductoId());

        return pedidoMapper.toResponse(pedido, usuario, producto);
    }

}