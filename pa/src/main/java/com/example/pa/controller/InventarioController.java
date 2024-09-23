package com.example.pa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pa.entity.Inventario;
import com.example.pa.service.ProductoService;

@RestController
public class InventarioController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/inventario")
    public List<Inventario> getInventario() {
        return productoService.obtenerInventarioCompleto(); // Llama al método para obtener el inventario
    }
}
