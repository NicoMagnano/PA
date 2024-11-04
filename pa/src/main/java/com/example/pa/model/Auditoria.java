package com.example.pa.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario; // ID o email del usuario
    private String accion;  // Tipo de acción (ej., LOGIN, REGISTRO, ACTUALIZAR_PERFIL)
    private String detalles; // Detalles adicionales del evento
    private LocalDateTime fecha; // Fecha y hora del evento

    // Getters y Setters
    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario=usuario;
    }

    public String getAccion(){
        return accion;
    }
    
    public void setAccion(String accion){
        this.accion=accion;
    }

    public String getDetalles(){
        return detalles;
    }

    public void setDetalles(String detalles){
        this.detalles=detalles;
    }

    public LocalDateTime getFecha(){
        return fecha;
    }

    public void setFecha(LocalDateTime fecha){
        this.fecha=fecha;
    }

}
