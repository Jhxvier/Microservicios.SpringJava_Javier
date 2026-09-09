package com.taller.ms_productos.mapper;

import com.taller.ms_productos.dto.ProductoRequestDTO;
import com.taller.ms_productos.dto.ProductoResponseDTO;
import com.taller.ms_productos.model.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-08T22:00:41-0600",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12 (Oracle Corporation)"
)
@Component
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public Producto toEntity(ProductoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setNombre( dto.getNombre() );
        producto.setPrecio( dto.getPrecio() );
        producto.setStock( dto.getStock() );

        return producto;
    }

    @Override
    public ProductoResponseDTO toResponse(Producto producto) {
        if ( producto == null ) {
            return null;
        }

        ProductoResponseDTO productoResponseDTO = new ProductoResponseDTO();

        productoResponseDTO.setId( producto.getId() );
        productoResponseDTO.setNombre( producto.getNombre() );
        productoResponseDTO.setPrecio( producto.getPrecio() );
        productoResponseDTO.setStock( producto.getStock() );
        productoResponseDTO.setStockBajo( producto.isStockBajo() );

        return productoResponseDTO;
    }

    @Override
    public List<ProductoResponseDTO> toResponseList(List<Producto> productos) {
        if ( productos == null ) {
            return null;
        }

        List<ProductoResponseDTO> list = new ArrayList<ProductoResponseDTO>( productos.size() );
        for ( Producto producto : productos ) {
            list.add( toResponse( producto ) );
        }

        return list;
    }

    @Override
    public void updateEntity(Producto producto, ProductoRequestDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNombre() != null ) {
            producto.setNombre( dto.getNombre() );
        }
        if ( dto.getPrecio() != null ) {
            producto.setPrecio( dto.getPrecio() );
        }
        if ( dto.getStock() != null ) {
            producto.setStock( dto.getStock() );
        }
    }
}
