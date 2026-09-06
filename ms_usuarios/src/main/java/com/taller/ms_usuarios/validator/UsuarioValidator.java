package com.taller.ms_usuarios.validator;

import com.taller.ms_usuarios.common.exceptions.DuplicateEmailException;
import com.taller.ms_usuarios.dto.UsuarioRequestDTO;
import com.taller.ms_usuarios.model.Usuario;
import com.taller.ms_usuarios.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

//REGLAS DE NEGOCIO
@Component
@Data
@AllArgsConstructor
public class UsuarioValidator {

    //inyección de dependencias
    private final UsuarioRepository usuarioRepository;

    //VALIDAR EL CORREO
    public void checkEmailUniqueCreate (String email) {

        if (usuarioRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(email);
        }

    }

    //Validar correo a la hora de actualizar
    public void checkEmailUniqueUpdate (Usuario usuarioActual,  String email) {
        boolean cambiarEmail = !usuarioActual.getEmail().equalsIgnoreCase(email);
        if (cambiarEmail && usuarioRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(email);
        }
    }

}
