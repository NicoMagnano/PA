package com.example.pa.controller.DTO.ConsultaDTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ConsultaDTO {

    private String descripcion;
    private List<MultipartFile> archivos;


      //Constructor
    public ConsultaDTO(String descripcion, List<MultipartFile> archivos) {
        this.descripcion = descripcion;
        this.archivos = archivos;
        }

    // Getters y setters
    public String getDescripcion() {
        return descripcion;
        }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public List<MultipartFile> getArchivos() {
        return archivos;
    }
    public void setArchivos(List<MultipartFile> archivos) {
        this.archivos = archivos;
    }
}
