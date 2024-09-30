package com.example.pa.controller;

<<<<<<< HEAD
import com.example.pa.model.Marca;
import com.example.pa.model.Producto;
=======
import com.example.pa.controller.DTO.MarcaDTO.MarcaDTO;
>>>>>>> 5fde2b6b40d5f6bb0f930384854414f96cc2cae0
import com.example.pa.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.pa.dto.marca.MarcaDTO;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

<<<<<<< HEAD
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
=======
    @PostMapping
    public ResponseEntity<MarcaDTO> crearMarca(@RequestBody MarcaDTO marcaDTO) {
        MarcaDTO nuevaMarca = marcaService.crearMarca(marcaDTO);
        return new ResponseEntity<>(nuevaMarca, HttpStatus.CREATED);
    }

     @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> actualizarMarca(@PathVariable Long id, @RequestBody MarcaDTO marcaDTO) {
        MarcaDTO marcaActualizada = marcaService.actualizarMarca(id, marcaDTO);
        return marcaActualizada != null
            ? new ResponseEntity<>(marcaActualizada, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
>>>>>>> 5fde2b6b40d5f6bb0f930384854414f96cc2cae0
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id) {
        marcaService.eliminarMarca(id);
<<<<<<< HEAD
        return ResponseEntity.noContent().build();
=======
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<MarcaDTO>> obtenerMarcas() {
        List<MarcaDTO> marcas = marcaService.obtenerMarcas();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
>>>>>>> 5fde2b6b40d5f6bb0f930384854414f96cc2cae0
    }
    }


