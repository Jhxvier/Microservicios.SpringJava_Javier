package com.taller.ms_usuarios.mapper;

import com.taller.ms_usuarios.dto.UsuarioRequestDTO;
import com.taller.ms_usuarios.dto.UsuarioResponseDTO;
import com.taller.ms_usuarios.model.Usuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    //convierte de requestDTO a entity
    @Mapping(target = "id", ignore = true)
    Usuario toEntity(UsuarioRequestDTO dto);

    //convierte de entity a responseDTO
    UsuarioResponseDTO toResponse(Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(
            @MappingTarget Usuario usuario,
            UsuarioRequestDTO dto

    );

    List<UsuarioResponseDTO> toReponseDTOList(List<Usuario> usuarios);

}
