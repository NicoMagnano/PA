package com.example.pa.controller;

import com.example.pa.model.Marca;
import com.example.pa.model.Producto;
import com.example.pa.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.pa.dto.marca.MarcaDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public List<MarcaDTO> getAllMarcas() {
        return marcaService.getAllMarcas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDTO> getMarcaById(@PathVariable Long id) {
        MarcaDTO marca = marcaService.getMarcaById(id);
        if (marca != null) {
            return ResponseEntity.ok(marca);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> createMarca(@RequestBody MarcaDTO marcaDTO) {
        MarcaDTO nuevaMarca = marcaService.createMarca(marcaDTO);
        return ResponseEntity.ok(nuevaMarca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> updateMarca(@PathVariable Long id, @RequestBody MarcaDTO marcaDTO) {
        MarcaDTO updatedMarca = marcaService.updateMarca(id, marcaDTO);
        if (updatedMarca != null) {
            return ResponseEntity.ok(updatedMarca);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id) {
        marcaService.eliminarMarca(id);
        return ResponseEntity.noContent().build();
    }
    }


