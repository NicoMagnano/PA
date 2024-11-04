package com.example.pa.model;

import jakarta.persistence.*;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RolNombre nombre;

   public Long getId() {
    return id;
} 

// Setter para id
public void setId(Long id) {
    this.id = id;
}

// Getter para nombre
public RolNombre getNombre() {
    return nombre;
}

// Setter para nombre
public void setNombre(RolNombre nombre) {
    this.nombre = nombre;
}
}

