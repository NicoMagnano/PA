package com.example.pa.controller.DTO.UsuarioDTO;

public class RegistroUsuarioDTO {
    
    private String nombre;
    private String email;
    private String password;
    private String confirmPassword;

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmarPassword() {
        return confirmPassword;
    }

    public void setConfirmarPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

