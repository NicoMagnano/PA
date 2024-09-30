package com.example.pa.controller.DTO.CategoriaDTO;

import jakarta.validation.constraints.NotNull;

public class EliminarCategoriaDto {
    
    @NotNull(message = "El ID no puede ser nulo")
    private Long id;
}
