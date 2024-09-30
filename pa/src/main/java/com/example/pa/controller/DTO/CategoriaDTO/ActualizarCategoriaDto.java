package com.example.pa.controller.DTO.CategoriaDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ActualizarCategoriaDto {
    
    @Size(min = 2, max = 32, message = "El Nombre debe tener entre 2 y 32 caracteres.")
    @NotEmpty(message = "El Nombre no puede estar vacía.")
    private String nombre;
    
}
