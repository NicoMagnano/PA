package com.example.pa.dto.marca;

public class MarcaDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private boolean activo;
    // Constructor vacío
    public MarcaDTO() {}

    // Constructor con parámetros
    public MarcaDTO(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
