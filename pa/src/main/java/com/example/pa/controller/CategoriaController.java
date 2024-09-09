package com.example.pa.controller;

//Este controlador proporciona una API básica para realizar operaciones CRUD sobre la entidad Categoria en la aplicación

import com.example.pa.entity.Categoria;
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

    @GetMapping // Método para manejar solicitudes GET en la ruta base '/categorias'. Devuelve una lista de todas las categorías.
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}") // Método para manejar solicitudes GET en la ruta '/categorias/{id}', donde {id} es el identificador de la categoría. Devuelve una categoría específica.
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.obtenerCategoriaPorId(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping // Método para manejar solicitudes POST en la ruta base '/categorias'. Crea una nueva categoría.
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.guardarCategoria(categoria);
    }

    @PutMapping("/{id}") // Método para manejar solicitudes PUT en la ruta '/categorias/{id}', donde {id} es el identificador de la categoría. Actualiza una categoría existente.
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaActualizada) {
        try {
            Categoria categoria = categoriaService.actualizarCategoria(id, categoriaActualizada);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // Método para manejar solicitudes DELETE en la ruta '/categorias/{id}', donde {id} es el identificador de la categoría. Elimina una categoría existente.
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build(); // Devuelve una respuesta HTTP 204 (No Content) para indicar que la eliminación fue exitosa y no hay contenido en la respuesta.
    }
}
