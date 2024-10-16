package com.example.pa.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Compra {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Producto> productos = new ArrayList<>();
    public Compra() {}
    
    public void agregarProducto(Producto producto)
    {
        this.productos.add(producto);
    };
    public void eliminarProducto(long id_producto){
        this.productos.removeIf(producto->producto.getId().equals(id_producto));
    };
    
    // public double getTotal() {
    //     return productos.stream().mapToDouble(p -> p.getPrecio() * p.getCantidad()).sum();
    // }
    
}
