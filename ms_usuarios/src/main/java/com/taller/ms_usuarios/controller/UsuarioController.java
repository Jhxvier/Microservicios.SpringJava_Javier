package com.taller.ms_usuarios.controller;

import com.taller.ms_usuarios.dto.UsuarioRequestDTO;
import com.taller.ms_usuarios.dto.UsuarioResponseDTO;
import com.taller.ms_usuarios.model.Usuario;
import com.taller.ms_usuarios.repository.UsuarioRepository;
import com.taller.ms_usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    //inyección de dependencia
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.obtenerPorId(id));
    }


    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioRequestDTO usuarioDTO){

        //recibo dto y requiero mapear a entity
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(usuarioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> modificar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO usuarioDTO){

        //recibo dto y requiero mapear a entity
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.actualizar(id, usuarioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> eliminar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO usuarioDTO){

        //recibo dto y requiero mapear a entity
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.eliminar(id));
    }




}
