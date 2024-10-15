package com.example.pa.controller;


import com.example.pa.service.CategoriaService;
import com.example.pa.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getcategoriaById(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.findById(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Categoria createCategoria(@RequestBody Categoria categoria) {
        return categoriaService.save(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        if (!categoriaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        categoria.setId(id);
        return ResponseEntity.ok(categoriaService.save(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok("categoria ocultada exitosamente");
    }

    @PutMapping("/recuperar/{id}")
    public ResponseEntity<String> recuperarCategoria(@PathVariable Long id) {
        categoriaService.recuperarCategoria(id);
        return ResponseEntity.ok("Marca recuperada exitosamente");
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Categoria>> obtenerCategoriasActivas() {
        List<Categoria> categoriasActivas = categoriaService.obtenerCategoriasActivas();
        return ResponseEntity.ok(categoriasActivas);
    }
}