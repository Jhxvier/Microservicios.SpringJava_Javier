package com.taller.ms_pedidos.mapper;

import com.taller.ms_pedidos.dto.PedidoRequestDTO;
import com.taller.ms_pedidos.dto.PedidoResponseDTO;
import com.taller.ms_pedidos.dto.ProductoDTO;
import com.taller.ms_pedidos.dto.UsuarioDTO;
import com.taller.ms_pedidos.model.Pedido;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-08T22:00:23-0600",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12 (Oracle Corporation)"
)
@Component
public class PedidoMapperImpl implements PedidoMapper {

    @Override
    public Pedido toEntity(PedidoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Pedido pedido = new Pedido();

        pedido.setUsuarioId( dto.getUsuarioId() );
        pedido.setProductoId( dto.getProductoId() );
        pedido.setCantidad( dto.getCantidad() );

        return pedido;
    }

    @Override
    public PedidoResponseDTO toResponse(Pedido pedido, UsuarioDTO usuarioDTO, ProductoDTO productoDTO) {
        if ( pedido == null && usuarioDTO == null && productoDTO == null ) {
            return null;
        }

        PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO();

        if ( pedido != null ) {
            pedidoResponseDTO.setId( pedido.getId() );
            pedidoResponseDTO.setCantidad( pedido.getCantidad() );
            pedidoResponseDTO.setTotal( pedido.getTotal() );
            pedidoResponseDTO.setFecha( pedido.getFecha() );
            pedidoResponseDTO.setEstado( pedido.getEstado() );
        }
        pedidoResponseDTO.setUsuario( usuarioDTO );
        pedidoResponseDTO.setProducto( productoDTO );

        return pedidoResponseDTO;
    }
}
