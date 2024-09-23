package com.example.pa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pa.model.SubCategoria;
import com.example.pa.service.SubCategoriaService;

@RestController
@RequestMapping("/api/subcategorias")
public class SubCategoriaController {

    @Autowired
    private SubCategoriaService subcategoriaService;

    // Endpoint para listar subcategorías activas
    @GetMapping
    public List<SubCategoria> listarSubcategorias() {
        return subcategoriaService.listarSubcategoriasActivas();
    }

    // Endpoint para crear una nueva subcategoría
    @PostMapping
    public SubCategoria crearSubcategoria(@RequestBody SubCategoria subcategoria) {
        return subcategoriaService.crearSubcategoria(subcategoria);
    }

    // Endpoint para actualizar una subcategoría existente
    @PutMapping("/{id}")
    public SubCategoria actualizarSubcategoria(@PathVariable Long id, @RequestBody SubCategoria subcategoria) {
        return subcategoriaService.actualizarSubcategoria(id, subcategoria);
    }

    // Endpoint para "eliminar" una subcategoría
    @DeleteMapping("/{id}")
    public void eliminarSubcategoria(@PathVariable Long id) {
        subcategoriaService.eliminarSubcategoria(id);
    }

    // Endpoint para "recuperar" una subcategoría eliminada
    @PutMapping("/recuperar/{id}")
    public SubCategoria recuperarSubcategoria(@PathVariable Long id) {
        return subcategoriaService.recuperarSubcategoria(id);
    }
}