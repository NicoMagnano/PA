package com.example.pa.controller.DTO.MarcaDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EliminarMarcaDto {
    
    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    
}
