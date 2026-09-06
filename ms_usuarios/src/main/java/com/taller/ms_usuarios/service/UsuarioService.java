package com.taller.ms_usuarios.service;

import com.taller.ms_usuarios.common.exceptions.UsuarioNotFoundException;
import com.taller.ms_usuarios.dto.UsuarioRequestDTO;
import com.taller.ms_usuarios.dto.UsuarioResponseDTO;
import com.taller.ms_usuarios.mapper.UsuarioMapper;
import com.taller.ms_usuarios.model.Usuario;
import com.taller.ms_usuarios.repository.UsuarioRepository;
import com.taller.ms_usuarios.validator.UsuarioValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioValidator usuarioValidator;

    public List<UsuarioResponseDTO> listarTodos() {
        List<Usuario> lista = usuarioRepository.findAll();
        return usuarioMapper.toReponseDTOList(lista);
    }

    public UsuarioResponseDTO obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()->new UsuarioNotFoundException(id));

        //mapeo
        return usuarioMapper.toResponse(usuario);
    }


    public UsuarioResponseDTO crear(UsuarioRequestDTO usuarioDTO){


        //reglas de negocio
        usuarioValidator.checkEmailUniqueCreate(usuarioDTO.getEmail());


        //mapear de DTO a entity para enviar a guardar
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);

        usuario = usuarioRepository.save(usuario);

        //despues de guardar mappear de entity a responseDTO
        return usuarioMapper.toResponse(usuario);
    }


    public UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO usuarioDTO ) {
        //validar que exista
        var usuarioActualBD = usuarioRepository.findById(id).orElseThrow(()->new UsuarioNotFoundException(id));

        usuarioValidator.checkEmailUniqueUpdate(usuarioActualBD, usuarioDTO.getEmail());

        //mapeo
        usuarioMapper.updateEntity(usuarioActualBD, usuarioDTO);

        usuarioActualBD = usuarioRepository.save(usuarioActualBD);

        //mapeo responde dto

        return usuarioMapper.toResponse(usuarioActualBD);
    }

    public UsuarioResponseDTO eliminar(Long id) {
        //validar que exista
        var usuarioActualBD = usuarioRepository.findById(id).orElseThrow(()->new UsuarioNotFoundException(id));

        usuarioRepository.delete(usuarioActualBD);

        //mapeo a response dto

        return usuarioMapper.toResponse(usuarioActualBD);

    }

}
