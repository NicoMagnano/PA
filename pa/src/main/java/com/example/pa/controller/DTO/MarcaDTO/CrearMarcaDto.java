package com.example.pa.controller.DTO.MarcaDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrearMarcaDto {
   
    @Size(min = 2, max = 32, message = "El Nombre debe tener entre 2 y 32 caracteres.")
    @NotEmpty(message = "El Nombre no puede estar vacía.")
    private String nombre;

    @Size(min = 1, max = 128, message = "La descripción debe tener entre 1 y 128 caracteres.")
    @NotEmpty(message = "La descripción no puede estar vacía.")
    private String descripcion;

    
}
