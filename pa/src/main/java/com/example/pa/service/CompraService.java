package com.example.pa.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pa.model.Compra;
import com.example.pa.repository.CompraRepository;


@Service


 
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;
    
    public  List<Compra> findAll() {
        return compraRepository.findAll();
    }
    

    // Buscar una compra por ID
    public Compra findById(Long id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + id));
    }

    // Crear o actualizar una compra
    public Compra saveOrUpdate(Compra compra) {
        return compraRepository.save(compra);
    }


    // Agregar un producto a una compra
    public Compra agregarProducto(Long compraId, String nombre, String descripcion, double precio, int stock) {
        Compra compra = findById(compraId); // Buscar la compra
        compra.crearYAgregarProducto(nombre, descripcion, precio, stock); // Agregar el producto
        return compraRepository.save(compra); // Guardar la compra actualizada
    }

    // Eliminar un producto de una compra
    public Compra eliminarProducto(Long compraId, Long productoId) {
        Compra compra = findById(compraId); // Buscar la compra
        compra.eliminarProducto(productoId); // Eliminar el producto por ID
        return compraRepository.save(compra); // Guardar la compra actualizada
    }

}