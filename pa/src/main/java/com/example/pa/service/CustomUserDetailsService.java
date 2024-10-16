package com.example.pa.service;

import com.example.pa.model.Usuario;
import com.example.pa.repository.UsuarioRepository;
import java.util.ArrayList; // Importa la clase ArrayList para crear una lista de roles
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails; // Interfaz que representa los detalles del usuario
import org.springframework.security.core.userdetails.UserDetailsService; // Interfaz para cargar detalles de usuario
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Excepción para usuario no encontrado
import org.springframework.stereotype.Service;

@Service // Anotación que indica que esta clase es un servicio de Spring
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired // Inyección automática del repositorio de usuarios
    private UsuarioRepository usuarioRepository;

    @Override // Implementación del método de UserDetailsService
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar el usuario en la base de datos usando el email proporcionado
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        // Retorna un objeto UserDetails, que contiene la información del usuario
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(), // Nombre de usuario (email)
                usuario.getPassword(), // Contraseña del usuario
                new ArrayList<>()); // Lista de autoridades (roles) del usuario. Aquí se puede agregar roles si es necesario.
    }
}
