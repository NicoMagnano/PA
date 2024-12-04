package com.example.pa.controller;

import com.example.pa.controller.DTO.CategoriaDTO.CategoriaDTO;
import com.example.pa.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Método para crear una nueva categoría
    @PostMapping
    public ResponseEntity<CategoriaDTO> crearCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO nuevaCategoria = categoriaService.crearCategoria(categoriaDTO);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    // Método para actualizar una categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaActualizada = categoriaService.actualizarCategoria(id, categoriaDTO);
        return categoriaActualizada != null
            ? new ResponseEntity<>(categoriaActualizada, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Método para eliminar una categoría (eliminación lógica)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Método para recuperar una categoría previamente eliminada
    @PutMapping("/recuperar/{id}")
    public ResponseEntity<Void> recuperarCategoria(@PathVariable Long id) {
        categoriaService.recuperarCategoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Método para obtener todas las categorías no eliminadas
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> obtenerCategorias() {
        List<CategoriaDTO> categorias = categoriaService.obtenerCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
}
