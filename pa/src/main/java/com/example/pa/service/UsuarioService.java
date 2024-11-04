package com.example.pa.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pa.controller.DTO.UsuarioDTO.PerfilUsuarioDTO;
import com.example.pa.controller.DTO.UsuarioDTO.RegistroUsuarioDTO;
import com.example.pa.model.Rol;
import com.example.pa.model.RolNombre;
import com.example.pa.model.Usuario;
import com.example.pa.repository.RolRepository;
import com.example.pa.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import com.example.pa.service.AuditoriaService;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Inyectamos el servicio de auditoría
    @Autowired
    private AuditoriaService auditoriaService;

    @Autowired
    private RolRepository rolRepository;

    // Método para registrar un nuevo usuario
    public Usuario registrar(RegistroUsuarioDTO dto) throws Exception {
        System.out.println("Contraseña: " + dto.getPassword());
        System.out.println("Confirmación de Contraseña: " + dto.getConfirmarPassword());
        
        if (!dto.getPassword().equals(dto.getConfirmarPassword())) {
            throw new Exception("Las contraseñas no coinciden");
        }

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new Exception("El correo electrónico ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Registrar evento de auditoría para el registro de usuario
        auditoriaService.registrarEvento(usuarioGuardado.getEmail(), "REGISTRO", "Registro de nuevo usuario");

        return usuarioGuardado;
    }

    // Método para cargar un usuario por su email
    public UserDetails cargarUsuarioPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        
        return new User(usuario.getEmail(), usuario.getPassword(), new ArrayList<>());
    }

    // Obtener perfil actual
    public PerfilUsuarioDTO obtenerPerfil(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        PerfilUsuarioDTO dto = new PerfilUsuarioDTO();
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setDireccionEnvio(usuario.getDireccionEnvio());
        return dto;
    }

    // Actualizar perfil con verificación de correo único
    @Transactional
    public boolean actualizarPerfil(Long usuarioId, PerfilUsuarioDTO perfilDTO) {
        if (usuarioRepository.existsByEmailAndIdNot(perfilDTO.getCorreo(), usuarioId)) {
            return false;
        }
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        usuario.setNombre(perfilDTO.getNombre());
        usuario.setEmail(perfilDTO.getCorreo());
        usuario.setTelefono(perfilDTO.getTelefono());
        usuario.setDireccionEnvio(perfilDTO.getDireccionEnvio());

        usuarioRepository.save(usuario);

        // Registrar evento de auditoría para la actualización de perfil
        auditoriaService.registrarEvento(usuario.getEmail(), "ACTUALIZAR_PERFIL", "Actualización de datos del perfil");

        return true;
    }

    public void asignarRolAuditor(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
       
        RolNombre rolNombre = RolNombre.AUDITOR; // Cambia esto si es necesario
        Rol auditorRole = rolRepository.findByNombre(rolNombre)
                .orElseThrow(() -> new RuntimeException("Rol AUDITOR no encontrado"));
    
        usuario.getRoles().add(auditorRole);
        usuarioRepository.save(usuario);
    }

    public Usuario obtenerUsuarioPorId(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
}

