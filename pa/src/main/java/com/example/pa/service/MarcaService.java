package com.example.pa.service;

import com.example.pa.model.Marca;
import com.example.pa.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> findById(Long id) {
        return marcaRepository.findById(id);
    }

    public Marca save(Marca marca) {
        return marcaRepository.save(marca);
    }

    public void eliminarMarca(Long id) {
        Marca marca = marcaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        marca.setActivo(false); // Oculta la marca
        marcaRepository.save(marca);
    }

    public void recuperarMarca(Long id) {
        Marca marca = marcaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        marca.setActivo(true); // Recupera la marca
        marcaRepository.save(marca);
    }

    public List<Marca> obtenerMarcasActivas() {
        return marcaRepository.findByActivo(true);
    }
}