package com.taller.ms_productos.service;

import com.taller.ms_productos.dto.ProductoRequestDTO;
import com.taller.ms_productos.dto.ProductoResponseDTO;
import com.taller.ms_productos.exception.ProductoNotFoundException;
import com.taller.ms_productos.mapper.ProductoMapper;
import com.taller.ms_productos.model.Producto;
import com.taller.ms_productos.repository.ProductoRepository;
import com.taller.ms_productos.validator.ProductoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final ProductoValidator productoValidator;

    public List<ProductoResponseDTO> listarTodos() {
        List<Producto> lista = productoRepository.findAll();
        return productoMapper.toResponseList(lista);
    }

    public ProductoResponseDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        //mapeo
        return productoMapper.toResponse(producto);
    }

    public ProductoResponseDTO crear(ProductoRequestDTO productoDTO) {

        //reglas de negocio
        productoValidator.checkNombreUnicoCreate(productoDTO.getNombre());

        //mapear de DTO a entity para enviar a guardar
        Producto producto = productoMapper.toEntity(productoDTO);

        producto = productoRepository.save(producto);

        //después de guardar mapear de entity a responseDTO
        return productoMapper.toResponse(producto);
    }

    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO productoDTO) {
        //validar que exista
        var productoActualBD = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        //reglas de negocio
        productoValidator.checkNombreUnicoUpdate(productoActualBD, productoDTO.getNombre());

        //mapeo: actualizar la entidad existente conservando su id
        productoMapper.updateEntity(productoActualBD, productoDTO);

        productoActualBD = productoRepository.save(productoActualBD);

        //mapeo a responseDTO
        return productoMapper.toResponse(productoActualBD);
    }

    public ProductoResponseDTO descontarStock(Long id, int cantidad) {
        //validar que exista
        var productoActualBD = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        //reglas de negocio
        productoValidator.checkStockSuficiente(productoActualBD.getStock(), cantidad);

        //descontar el stock
        productoActualBD.setStock(productoActualBD.getStock() - cantidad);

        productoActualBD = productoRepository.save(productoActualBD);

        return productoMapper.toResponse(productoActualBD);
    }

    public ProductoResponseDTO reponerStock(Long id, int cantidad) {
        //validar que exista
        var productoActualBD = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        //reponer el stock
        productoActualBD.setStock(productoActualBD.getStock() + cantidad);

        productoActualBD = productoRepository.save(productoActualBD);

        return productoMapper.toResponse(productoActualBD);
    }

    public void eliminar(Long id) {
        //validar que exista
        var productoActualBD = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        //reglas de negocio
        productoValidator.checkProductoConStock(productoActualBD);

        productoRepository.delete(productoActualBD);

    }

}