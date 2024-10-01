package com.example.pa.service;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.pa.model.Producto;
import com.example.pa.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;


import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    // Repositorio para manejar productos
    private ProductoRepository productoRepository;

   
    //Listado de Productos Activos(True)
    public List<Producto> listarProductosActivos() {
        return productoRepository.findByActivoTrue(); 
    }
    
    //Creacion de un Nuevo Producto
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    //Actualizacion de Productos Existentes 
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Optional<Producto> optionalProducto = productoRepository.findById(id); // Buscar el Producto por ID
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            // Actualiza los campos del producto con los nuevos valores
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setCategoria(productoActualizado.getCategoria());
            producto.setSubcategoria(productoActualizado.getSubcategoria());
            producto.setImagenes(productoActualizado.getImagenes());
            producto.setStock(productoActualizado.getStock());
            producto.setActivo(productoActualizado.isActivo());// Actualiza Estado (Activo)
            return productoRepository.save(producto);// Guarda el producto actualizado
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    //Eliminacion de Producto (Cambio de estado a "Inactivo")
    public void eliminarProducto(Long id) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            producto.setActivo(false);  // Cambia Estado (inactivo)
            productoRepository.save(producto);// Guarda el producto actualizado
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }
    
    //Recuperacion de Producto (Cambio de estado a "Activo")
    public Producto recuperarProducto(Long id) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            producto.setActivo(true);  // Cambia Estado (Activo)
            return productoRepository.save(producto);// Guarda el producto actualizado
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    //Listado de Productos Activos(True) e Inactivos(false)
    public List<Producto> listarTodosLosProductos() {
        return (List<Producto>) productoRepository.findAll();
    }

    //Validacion de Precios Positivo
    @SuppressWarnings("unused")
    private void ValidarPrecio(double precio) {
        if (precio < 0) {
            throw new  IllegalArgumentException("El precio no puede ser negativo");
        }
    }

    //Verificacion de Stock Bajo
    public void VerificarStockBajo(Producto producto){
        //Si el stck esta por debajo del Umbral
        if (producto.getStock() < producto.getUmbralStockBajo()){
            //Generador de Alerta
            generarAlertaStockBajo(producto);// Llama al método para generar alerta
        }
    }

    // Método privado para generar una alerta de stock bajo
    private void generarAlertaStockBajo(Producto producto) {
        // Aquí va la lógica para enviar la alerta
        System.out.println("¡Alerta! El producto " + producto.getNombre() + " tiene el stock bajo.");
    }

    // Método para encontrar un producto por su ID
    public Producto findById(Long id) {
        Optional<Producto> productoOpt = productoRepository.findById(id);
        return productoOpt.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));
    }

     // Método para guardar un producto
    public void save(Producto producto) {
        // Aquí se puede agregar validaciones si es necesario
        productoRepository.save(producto);
    }

     
}
