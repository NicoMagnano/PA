package com.example.pa.controller.DTO.CategoriaDTO;

public class ConsultarCategoriaDto {
    
    @NotNull(message = "El ID no puede ser nulo")
    private Long id;
    
    @Size(min = 2, max = 32, message = "El Nombre debe tener entre 2 y 32 caracteres.")
    @NotEmpty(message = "El Nombre no puede estar vacía.")
    private String nombre;
}
