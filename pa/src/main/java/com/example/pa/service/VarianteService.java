package com.example.pa.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pa.entity.Variante;
import com.example.pa.repository.VarianteRepository;

@Service
public class VarianteService {

    @Autowired
    private VarianteRepository varianteRepository;

    // Método para listar solo variantes activas
    public List<Variante> listarVariantesActivas() {
        return varianteRepository.findByActivoTrue();
    }

    // Método para crear una nueva variante
    public Variante crearVariante(Variante variante) {
        return varianteRepository.save(variante);
    }

    // Método para actualizar una variante existente
    public Variante actualizarVariante(Long id, Variante varianteActualizada) {
        Optional<Variante> optionalVariante = varianteRepository.findById(id);
        if (optionalVariante.isPresent()) {
            Variante variante = optionalVariante.get();
            variante.setNombre(varianteActualizada.getNombre());
            variante.setActivo(varianteActualizada.isActivo());
            return varianteRepository.save(variante);
        } else {
            throw new RuntimeException("Variante no encontrada");
        }
    }

    // Método para "eliminar" una variante (cambio de estado a inactivo)
    public void eliminarVariante(Long id) {
        Optional<Variante> optionalVariante = varianteRepository.findById(id);
        if (optionalVariante.isPresent()) {
            Variante variante = optionalVariante.get();
            variante.setActivo(false);  // Cambia el estado a inactivo
            varianteRepository.save(variante);
        } else {
            throw new RuntimeException("Variante no encontrada");
        }
    }

    // Método para "recuperar" una variante eliminada
    public Variante recuperarVariante(Long id) {
        Optional<Variante> optionalVariante = varianteRepository.findById(id);
        if (optionalVariante.isPresent()) {
            Variante variante = optionalVariante.get();
            variante.setActivo(true);  // Cambia el estado a activo
            return varianteRepository.save(variante);
        } else {
            throw new RuntimeException("Variante no encontrada");
        }
    }

    public void save(VarianteService varianteService) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    public Variante findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
