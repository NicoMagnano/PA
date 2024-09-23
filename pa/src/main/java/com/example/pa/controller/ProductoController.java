package com.example.pa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pa.entity.Inventario;
import com.example.pa.entity.Producto;
import com.example.pa.service.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductosActivos();
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        if (producto.getPrecio() < 0) {
            return ResponseEntity.badRequest().body(null); // Respuesta de error
        }
        Producto nuevoProducto = productoService.crearProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        // Verificar si productoActualizado es null
        if (productoActualizado == null) {
            return ResponseEntity.badRequest().body(null); // Manejar el caso en el que no se pase un objeto en la solicitud
        }
    
        // Verificar si el precio es negativo
        if (productoActualizado.getPrecio() < 0) {
            return ResponseEntity.badRequest().body(null); // Manejar error de precio negativo
        }
    
        // Actualizar el producto si todo es válido
        Producto producto = productoService.actualizarProducto(id, productoActualizado);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }

    @PutMapping("/recuperar/{id}")
    public Producto recuperarProducto(@PathVariable Long id) {
        return productoService.recuperarProducto(id);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Producto> updateStock(@PathVariable Long id, @RequestParam int newStock) {
        Producto producto = productoService.findById(id);
        producto.setStock(newStock);
        productoService.VerificarStockBajo(producto); // Verificación del stock bajo
        productoService.save(producto);
        return ResponseEntity.ok(producto);
    }

    @PostMapping("/{id}/ajustar-stock")
    public ResponseEntity<Inventario> ajustarStock(
    @PathVariable Long id, 
    @RequestParam int cantidad, 
    @RequestParam String razon) {
    
    Producto product = productoService.findById(id);
    Inventario history = productoService.ajustarStock(product, cantidad, razon);
    return ResponseEntity.ok(history);
}
}