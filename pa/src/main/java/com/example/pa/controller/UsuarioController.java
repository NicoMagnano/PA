package com.example.pa.controller;

import com.example.pa.controller.DTO.UsuarioDTO.LoginDTO;
import com.example.pa.controller.DTO.UsuarioDTO.RegistroUsuarioDTO;
import com.example.pa.model.Usuario;
import com.example.pa.service.AuditoriaService;
import com.example.pa.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/usuarios") // Define la ruta base para todas las solicitudes en este controlador
public class UsuarioController {

    @Autowired // Inyección automática de la dependencia UsuarioService
    private UsuarioService usuarioService;

    @Autowired // Inyección automática del AuthenticationManager para manejar la autenticación
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuditoriaService auditoriaService;

    // Método para registrar un nuevo usuario
    @PostMapping("/registro") // Define que este método manejará solicitudes POST a "/api/usuarios/registro"
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid RegistroUsuarioDTO dto) {
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto, HttpServletRequest request) {
    try {

        // Intenta autenticar al usuario con las credenciales proporcionadas
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        // Si la autenticación es exitosa, registramos el evento en la auditoría
        auditoriaService.registrarEvento(dto.getEmail(), "INICIO_SESION", "Inicio de sesión exitoso");

        // Crea la sesión
        request.getSession().setAttribute("user", dto.getEmail());

        // Devuelve un mensaje de éxito y el estado HTTP 200 (OK)
        return new ResponseEntity<>("Login exitoso", HttpStatus.OK);
        
    } catch (AuthenticationException e) {
        // Si ocurre un error de autenticación, registra el fallo en la auditoría
        auditoriaService.registrarEvento(dto.getEmail(), "FALLO_INICIO_SESION", "Fallo en inicio de sesión: credenciales incorrectas");

        // Devuelve un mensaje de error y el estado HTTP 401 (UNAUTHORIZED)
        return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
    }
        }

       
       @PostMapping("/{id}/asignar-auditor")
       public ResponseEntity<String> asignarRolAuditor(@PathVariable Long id) {
           usuarioService.asignarRolAuditor(id);
           return ResponseEntity.ok("Rol AUDITOR asignado correctamente");
       }

       @PostMapping("/logout")
       public ResponseEntity<?> logout(HttpServletRequest request) {
           request.getSession().invalidate(); // Invalida la sesión del usuario
           return new ResponseEntity<>("Logout exitoso", HttpStatus.OK);
       }

       @GetMapping("/check")
       public ResponseEntity<?> checkSession(HttpServletRequest request) {
       String email = (String) request.getSession().getAttribute("user");
        if (email != null) {
        return new ResponseEntity<>(email, HttpStatus.OK);
        }
       return new ResponseEntity<>("No autenticado", HttpStatus.UNAUTHORIZED);
}

   
}


