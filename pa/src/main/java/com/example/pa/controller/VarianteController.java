package com.example.pa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pa.model.Variante;
import com.example.pa.service.VarianteService;

@RestController
@RequestMapping("/api/variantes")
public class VarianteController {

    @Autowired
    private VarianteService varianteService;

    // Endpoint para listar variantes activas
    @GetMapping
    public List<Variante> listarVariantes() {
        return varianteService.listarVariantesActivas();
    }

    // Endpoint para crear una nueva variante
    @PostMapping
    public Variante crearVariante(@RequestBody Variante variante) {
        return varianteService.crearVariante(variante);
    }

    // Endpoint para actualizar una variante existente
    @PutMapping("/{id}")
    public Variante actualizarVariante(@PathVariable Long id, @RequestBody Variante variante) {
        return varianteService.actualizarVariante(id, variante);
    }

    // Endpoint para "eliminar" una variante
    @DeleteMapping("/{id}")
    public void eliminarVariante(@PathVariable Long id) {
        varianteService.eliminarVariante(id);
    }

    // Endpoint para "recuperar" una variante eliminada
    @PutMapping("/recuperar/{id}")
    public Variante recuperarVariante(@PathVariable Long id) {
        return varianteService.recuperarVariante(id);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Variante> updateStock(@PathVariable Long id, @RequestParam int newStock) {
        Variante variant = varianteService.findById(id);
        variant.setStock(newStock);
        varianteService.save(varianteService);
        return ResponseEntity.ok(variant);
    }
}
