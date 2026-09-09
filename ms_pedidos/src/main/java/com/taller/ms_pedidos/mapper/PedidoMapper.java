package com.taller.ms_pedidos.mapper;

import com.taller.ms_pedidos.dto.PedidoRequestDTO;
import com.taller.ms_pedidos.dto.PedidoResponseDTO;
import com.taller.ms_pedidos.dto.ProductoDTO;
import com.taller.ms_pedidos.dto.UsuarioDTO;
import com.taller.ms_pedidos.model.Pedido;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    //convierte de requestDTO a entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Pedido toEntity(PedidoRequestDTO dto);

    //convierte de entity mas los DTOs de los otros microservicios a responseDTO
    @Mapping(target = "id", source = "pedido.id")
    @Mapping(target = "usuario", source = "usuarioDTO")
    @Mapping(target = "producto", source = "productoDTO")
    @Mapping(target = "cantidad", source = "pedido.cantidad")
    @Mapping(target = "total", source = "pedido.total")
    @Mapping(target = "fecha", source = "pedido.fecha")
    @Mapping(target = "estado", source = "pedido.estado")
    PedidoResponseDTO toResponse(Pedido pedido, UsuarioDTO usuarioDTO, ProductoDTO productoDTO);

}