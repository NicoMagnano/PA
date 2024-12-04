package com.example.pa.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pa.User.Role;
import com.example.pa.Jwt.JwtService;
import com.example.pa.User.User;
import com.example.pa.User.UserRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

import com.example.pa.service.AuditoriaService;  // Inyecta el servicio de auditoría

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuditoriaService auditoriaService;  // Inyecta el servicio de auditoría

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);

        // Registrar la auditoría
        auditoriaService.registrarAuditoria(request.getUsername(), "LOGIN", "Inicio de sesión exitoso");


        return AuthResponse.builder()
            .token(token)
            .build();

    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode( request.getPassword()))
            .email(request.getEmail())
            .lastname(request.lastname)
            .firstname(request.getFirstname())
            .direccionEnvio(request.getDireccionEnvio())
            .telefono(request.getTelefono())
            .role(Role.USER)
            .build();

        userRepository.save(user);

        // Registrar la auditoría
        auditoriaService.registrarAuditoria(request.getUsername(), "REGISTER", "Registro de usuario exitoso");

        //Genera el token
        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }

}