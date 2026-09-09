package com.taller.ms_pedidos.controller;

import com.taller.ms_pedidos.dto.PedidoRequestDTO;
import com.taller.ms_pedidos.dto.PedidoResponseDTO;
import com.taller.ms_pedidos.model.Pedido;
import com.taller.ms_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    //listar todos los pedidos (datos crudos, sin enriquecer)
    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    //detalle enriquecido (con datos de usuario y producto)
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.obtenerDetalle(id));
    }

    //crear un pedido (junta usuario + producto + stock)
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crear(@Valid @RequestBody PedidoRequestDTO pedidoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crearPedido(pedidoDTO));
    }

    //cancelar un pedido (reponer stock + cambiar estado)
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<PedidoResponseDTO> cancelar(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.cancelarPedido(id));
    }

}