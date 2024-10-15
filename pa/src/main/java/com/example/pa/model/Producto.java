package com.example.pa.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    private double precio;

    private String sku; //Codigo Unico de referencia para el Producto

    private int stock;

    private boolean activo=true; //Indicador del Producto (Activo/Inactivo)

    private int umbralStockBajo;

    //Almacenamiento de URLs de las imágenes del producto
    @ElementCollection
    private List<String> imagenes;

    //Relaciones (Categoria/SubCategoria/Variante)
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Variante> variantes;


//Getter 

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getSku() {
        return sku;
    }

    public int getStock() {
        return stock;
    }

    public boolean isActivo() {
        return activo;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public Categoria getCategoria() {
        return categoria;
    }



    public List<Variante> getVariantes() {
        return variantes;
    }

    public int getUmbralStockBajo() {
        return umbralStockBajo;
    }


//Setters
     
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public void setVariantes(List<Variante> variantes) {
        this.variantes = variantes;
    }
  
    public void setUmbralStockBajo(int umbralStockBajo) {
        this.umbralStockBajo = umbralStockBajo;
    }
}