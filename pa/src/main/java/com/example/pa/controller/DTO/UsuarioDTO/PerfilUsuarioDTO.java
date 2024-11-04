package com.example.pa.controller.DTO.UsuarioDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PerfilUsuarioDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Email(message = "El correo debe tener un formato válido")
    private String correo;

    @Size(min = 10, max = 15, message = "El número de teléfono debe tener entre 10 y 15 dígitos")
    private String telefono;

    private String direccionEnvio;

    // Getters y Setters
    
     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo){
        this.correo= correo;
    }

   public String getTelefono(){
    return telefono;
   }

   public void setTelefono(String telefono){
    this.telefono=telefono;
   }

   public String getDireccionEnvio(){
    return direccionEnvio;
   }

   public void setDireccionEnvio(String direccionEnvio){
    this.direccionEnvio=direccionEnvio;
   }

}
