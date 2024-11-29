package com.example.pa.controller;

import com.example.pa.model.Marca;
import com.example.pa.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public List<Marca> getAllMarcas() {
        return marcaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaById(@PathVariable Long id) {
        Optional<Marca> marca = marcaService.findById(id);
        return marca.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Marca createMarca(@RequestBody Marca marca) {
        return marcaService.save(marca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> updateMarca(@PathVariable Long id, @RequestBody Marca marca) {
        if (!marcaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        marca.setId(id);
        return ResponseEntity.ok(marcaService.save(marca));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMarca(@PathVariable Long id) {
        marcaService.eliminarMarca(id);
        return ResponseEntity.ok("Marca ocultada exitosamente");
    }

    @PutMapping("/recuperar/{id}")
    public ResponseEntity<String> recuperarMarca(@PathVariable Long id) {
        marcaService.recuperarMarca(id);
        return ResponseEntity.ok("Marca recuperada exitosamente");
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Marca>> obtenerMarcasActivas() {
        List<Marca> marcasActivas = marcaService.obtenerMarcasActivas();
        return ResponseEntity.ok(marcasActivas);
    }
    }


