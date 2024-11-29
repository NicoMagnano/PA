package com.example.pa.controller.Mapper;

import com.example.pa.controller.DTO.UsuarioDTO.RegistroUsuarioDTO;
import com.example.pa.model.Usuario;

public class UsuarioMapper {

    // Método estático para convertir un RegistroUsuarioDTO en un objeto Usuario
    public static Usuario toEntity(RegistroUsuarioDTO dto) {
        // Crea una nueva instancia de Usuario
        Usuario usuario = new Usuario();
        // Establece el nombre del usuario desde el DTO
        usuario.setNombre(dto.getNombre());
        // Establece el email del usuario desde el DTO
        usuario.setEmail(dto.getEmail());
        // Establece la contraseña del usuario desde el DTO
        //La contraseña debe ser encriptada antes de ser almacenada en la base de datos
        usuario.setPassword(dto.getPassword()); 
        return usuario; // Devuelve el objeto Usuario creado
    }
}
