package com.taller.ms_productos.controller;

import com.taller.ms_productos.dto.ProductoRequestDTO;
import com.taller.ms_productos.dto.ProductoResponseDTO;
import com.taller.ms_productos.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    //inyección de dependencia
    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crear(@Valid @RequestBody ProductoRequestDTO productoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(productoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO productoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.actualizar(id, productoDTO));
    }

    @PatchMapping("/{id}/descontar-stock")
    public ResponseEntity<ProductoResponseDTO> descontarStock(@PathVariable Long id, @RequestParam int cantidad) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.descontarStock(id, cantidad));
    }

    @PatchMapping("/{id}/reponer-stock")
    public ResponseEntity<ProductoResponseDTO> reponerStock(@PathVariable Long id, @RequestParam int cantidad) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.reponerStock(id, cantidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}