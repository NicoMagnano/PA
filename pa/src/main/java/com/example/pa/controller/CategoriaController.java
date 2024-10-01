package com.example.pa.controller;

import com.example.pa.model.Categoria;
import com.example.pa.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Anota esta clase como un controlador REST, lo que significa que maneja solicitudes HTTP y devuelve respuestas JSON.
@RequestMapping("/categorias") // Define la ruta base para todas las solicitudes en este controlador. En este caso, todas las rutas comienzan con '/categorias'.
public class CategoriaController {

    @Autowired // Inyecta automáticamente el servicio 'CategoriaService' en esta clase para poder usar sus métodos.
    private CategoriaService categoriaService;

     // Endpoint para Listar Categorias Activas

    @GetMapping // Método para manejar solicitudes GET en la ruta base '/categorias'. Devuelve una lista de todas las categorías.
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategoriasActivas();
    }

    @GetMapping("/{id}") // Método para manejar solicitudes GET en la ruta '/categorias/{id}', donde {id} es el identificador de la categoría. Devuelve una categoría específica.
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.obtenerCategoriaPorId(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Endpoint para crear una nueva categoría

    @PostMapping 
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.crearCategoria(categoria);
    }

     //Endpoint para Actualizar una Categoría Existente

    @PutMapping("/{id}") // Método para manejar solicitudes PUT en la ruta '/categorias/{id}', donde {id} es el identificador de la categoría. Actualiza una categoría existente.
    public Categoria actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        return categoriaService.actualizarCategoria(id, categoria);
    }

    //Endpoint para Eliminar una Categoría 

    @DeleteMapping("/{id}") // Método para manejar solicitudes DELETE en la ruta '/categorias/{id}', donde {id} es el identificador de la categoría. Elimina una categoría existente.
    public void eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
    }

    // Endpoint para Recuperar una Categoría Eliminada
    @PutMapping("/recuperar/{id}")
    public Categoria recuperarCategoria(@PathVariable Long id) {
        return categoriaService.recuperarCategoria(id);
    }

    
}
