package com.example.pa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pa.controller.DTO.UsuarioDTO.PerfilUsuarioDTO;
import com.example.pa.model.Usuario;
import com.example.pa.service.UsuarioService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/perfil")
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para obtener el perfil actual
    @GetMapping
    public ResponseEntity<PerfilUsuarioDTO> obtenerPerfil(@AuthenticationPrincipal Usuario usuario) {
        PerfilUsuarioDTO perfil = usuarioService.obtenerPerfil(usuario.getId());
        return ResponseEntity.ok(perfil);
    }

    // Endpoint para actualizar el perfil
    @PutMapping
    public ResponseEntity<String> actualizarPerfil(@AuthenticationPrincipal Usuario usuario,
                                                   @RequestBody PerfilUsuarioDTO perfilDTO) {
        boolean actualizado = usuarioService.actualizarPerfil(usuario.getId(), perfilDTO);
        if (actualizado) {
            return ResponseEntity.ok("Perfil actualizado correctamente.");
        }
        return ResponseEntity.status(409).body("El correo ya está en uso.");
    }
}



