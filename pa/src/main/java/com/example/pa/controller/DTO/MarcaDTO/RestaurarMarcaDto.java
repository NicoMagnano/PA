package com.example.pa.controller.DTO.MarcaDTO;

import jakarta.validation.constraints.NotNull;

public class RestaurarMarcaDto {

    @NotNull(message = "El ID no puede ser nulo")
    private Long id;
    
}
