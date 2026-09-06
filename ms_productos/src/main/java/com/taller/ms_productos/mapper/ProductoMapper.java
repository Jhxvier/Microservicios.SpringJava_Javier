package com.taller.ms_productos.mapper;

import com.taller.ms_productos.dto.ProductoRequestDTO;
import com.taller.ms_productos.dto.ProductoResponseDTO;
import com.taller.ms_productos.model.Producto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    //convierte de requestDTO a entity
    @Mapping(target = "id", ignore = true)
    Producto toEntity(ProductoRequestDTO dto);

    //convierte de entity a responseDTO
    //MapStruct mapea "stockBajo" automáticamente gracias a la convención
    //JavaBeans: isStockBajo() en Producto == propiedad "stockBajo".
    ProductoResponseDTO toResponse(Producto producto);

    List<ProductoResponseDTO> toResponseList(List<Producto> productos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(
            @MappingTarget Producto producto,
            ProductoRequestDTO dto

    );
}