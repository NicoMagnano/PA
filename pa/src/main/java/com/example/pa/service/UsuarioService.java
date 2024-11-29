package com.example.pa.service;

import java.util.ArrayList; // Importa la clase ArrayList para usarla más adelante

import org.springframework.beans.factory.annotation.Autowired; // Anotación para la inyección de dependencias
import org.springframework.stereotype.Service; // Anotación que indica que esta clase es un servicio de Spring
import com.example.pa.controller.DTO.UsuarioDTO.RegistroUsuarioDTO; // DTO para el registro de usuario
import com.example.pa.model.Usuario; // Modelo de usuario
import com.example.pa.repository.UsuarioRepository; // Repositorio de usuario
import org.springframework.security.crypto.password.PasswordEncoder; // Interfaz para codificar contraseñas
import org.springframework.security.core.userdetails.UserDetails; // Interfaz que representa los detalles del usuario
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Excepción para usuario no encontrado
import org.springframework.security.core.userdetails.User; // Implementación de UserDetails

@Service // Marca la clase como un servicio que será manejado por el contenedor de Spring
public class UsuarioService {

    @Autowired // Inyección automática del repositorio de usuarios
    private UsuarioRepository usuarioRepository;

    @Autowired // Inyección automática del codificador de contraseñas
    private PasswordEncoder passwordEncoder;

    // Método para registrar un nuevo usuario
    public Usuario registrar(RegistroUsuarioDTO dto) throws Exception {
        // Imprime las contraseñas recibidas para depuración
        System.out.println("Contraseña: " + dto.getPassword());
        System.out.println("Confirmación de Contraseña: " + dto.getConfirmarPassword());
        
        // Validar que las contraseñas coincidan
        if (!dto.getPassword().equals(dto.getConfirmarPassword())) {
            throw new Exception("Las contraseñas no coinciden");
        }

        // Validar si el email ya existe en la base de datos
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new Exception("El correo electrónico ya está registrado");
        }

        // Crear un nuevo objeto Usuario con la contraseña encriptada
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword())); // Se encripta la contraseña

        // Guardar el usuario en la base de datos y retornar el objeto Usuario guardado
        return usuarioRepository.save(usuario);
    }

    // Método para cargar un usuario por su email
    public UserDetails cargarUsuarioPorEmail(String email) {
        // Buscar el usuario en la base de datos usando el email proporcionado
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        
        // Retornar un objeto User que implementa UserDetails, conteniendo email y contraseña
        return new User(usuario.getEmail(), usuario.getPassword(), new ArrayList<>()); // Aquí se puede agregar roles si es necesario
    }
}
