package com.example.pa.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pa.model.SubCategoria;
import com.example.pa.repository.SubCategoriaRepository;

@Service
public class SubCategoriaService {

    @Autowired
    private SubCategoriaRepository subcategoriaRepository;

    //Listado de SubCategorías Activas
    public List<SubCategoria> listarSubcategoriasActivas() {
        return subcategoriaRepository.findByActivoTrue();
    }

    //Creacion de una Nueva SubCategoría
    public SubCategoria crearSubcategoria(SubCategoria subcategoria) {
        return subcategoriaRepository.save(subcategoria);
    }

    //Actualizacion de una SubCategoría Existente
    public SubCategoria actualizarSubcategoria(Long id, SubCategoria subcategoriaActualizada) {
        Optional<SubCategoria> optionalSubcategoria = subcategoriaRepository.findById(id);
        if (optionalSubcategoria.isPresent()) {
            SubCategoria subcategoria = optionalSubcategoria.get();
            subcategoria.setNombre(subcategoriaActualizada.getNombre());
            subcategoria.setActivo(subcategoriaActualizada.isActivo());
            return subcategoriaRepository.save(subcategoria);
        } else {
            throw new RuntimeException("Subcategoría no encontrada");
        }
    }

    //Eliminacion de una SubCategoría (Cambio de Estado "Inactivo")
    public void eliminarSubcategoria(Long id) {
        Optional<SubCategoria> optionalSubcategoria = subcategoriaRepository.findById(id);
        if (optionalSubcategoria.isPresent()) {
            SubCategoria subcategoria = optionalSubcategoria.get();
            subcategoria.setActivo(false);  // Cambia el estado a inactivo
            subcategoriaRepository.save(subcategoria);
        } else {
            throw new RuntimeException("Subcategoría no encontrada");
        }
    }

    //Recuperacion de una SubCategoría (Cambio de Estado "Activo")
    public SubCategoria recuperarSubcategoria(Long id) {
        Optional<SubCategoria> optionalSubcategoria = subcategoriaRepository.findById(id);
        if (optionalSubcategoria.isPresent()) {
            SubCategoria subcategoria = optionalSubcategoria.get();
            subcategoria.setActivo(true);  // Cambia el estado a activo
            return subcategoriaRepository.save(subcategoria);
        } else {
            throw new RuntimeException("Subcategoría no encontrada");
        }
    }
}
