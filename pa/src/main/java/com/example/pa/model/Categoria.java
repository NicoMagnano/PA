package com.example.pa.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private boolean activo = true; //Indicador de Categoria (Activa/Inactiva)

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<SubCategoria> subcategorias;

    //Contructor
    public Categoria(Long id, String nombre, boolean activo, List<SubCategoria> subcategorias) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.subcategorias = subcategorias;
    }

    //Getter
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public List<SubCategoria> getSubcategorias() {
        return subcategorias;
    }

    

    //Setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setSubcategorias(List<SubCategoria> subcategorias) {
        this.subcategorias = subcategorias;
    }

}


