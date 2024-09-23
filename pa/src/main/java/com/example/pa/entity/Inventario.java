package com.example.pa.entity;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Inventario {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Producto producto;
    
    private int cantidadAjustada;
    
    private String razon; // Motivo del ajuste (venta, devolución, etc.)
    
    private LocalDateTime fechaAjuste;

    //Getter
   
    public Long getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidadAjustada() {
        return cantidadAjustada;
    }

    public String getRazon() {
        return razon;
    }

    public LocalDateTime getFechaAjuste() {
        return fechaAjuste;
    }

    //Setter

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidadAjustada(int cantidadAjustada) {
        this.cantidadAjustada = cantidadAjustada;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public void setFechaAjuste(LocalDateTime fechaAjuste) {
        this.fechaAjuste = fechaAjuste;
    }
    
}


