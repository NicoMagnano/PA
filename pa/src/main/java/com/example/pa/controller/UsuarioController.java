package com.example.pa.controller;

import com.example.pa.controller.DTO.UsuarioDTO.LoginDTO;
import com.example.pa.controller.DTO.UsuarioDTO.RegistroUsuarioDTO;
import com.example.pa.model.Usuario;
import com.example.pa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/usuarios") // Define la ruta base para todas las solicitudes en este controlador
public class UsuarioController {

    @Autowired // Inyección automática de la dependencia UsuarioService
    private UsuarioService usuarioService;

    @Autowired // Inyección automática del AuthenticationManager para manejar la autenticación
    private AuthenticationManager authenticationManager;

    // Método para registrar un nuevo usuario
    @PostMapping("/registro") // Define que este método manejará solicitudes POST a "/api/usuarios/registro"
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroUsuarioDTO dto) {
        try {
            // Llama al servicio para registrar el nuevo usuario usando los datos del DTO
            Usuario nuevoUsuario = usuarioService.registrar(dto);
            // Devuelve una respuesta con el usuario creado y el estado HTTP 201 (CREATED)
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            // Si ocurre una excepción, devuelve un mensaje de error y el estado HTTP 400 (BAD REQUEST)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Método para iniciar sesión
    @PostMapping("/login") // Define que este método manejará solicitudes POST a "/api/usuarios/login"
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            // Intenta autenticar al usuario con las credenciales proporcionadas
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
            );
            // Si la autenticación es exitosa, devuelve un mensaje de éxito y el estado HTTP 200 (OK)
            return new ResponseEntity<>("Login exitoso", HttpStatus.OK);
        } catch (AuthenticationException e) {
            // Si ocurre un error de autenticación, devuelve un mensaje de error y el estado HTTP 401 (UNAUTHORIZED)
            return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
        }
    }
}


