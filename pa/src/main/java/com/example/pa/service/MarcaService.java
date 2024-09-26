package com.example.pa.service;

import com.example.pa.model.Marca;
import com.example.pa.dto.marca.MarcaDTO;
import com.example.pa.mapper.MarcaMapper;
import com.example.pa.repository.MarcaRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private MarcaMapper marcaMapper;

    public List<MarcaDTO> getAllMarcas() {
        return marcaRepository.findAll()
                              .stream()
                              .map(marcaMapper::toDto)
                              .collect(Collectors.toList());
    }

    public MarcaDTO getMarcaById(Long id) {
        Optional<Marca> marca = marcaRepository.findById(id);
        return marca.map(marcaMapper::toDto).orElse(null);
    }

    public MarcaDTO createMarca(MarcaDTO marcaDTO) {
        Marca marca = marcaMapper.toEntity(marcaDTO);
        marca = marcaRepository.save(marca);
        return marcaMapper.toDto(marca);
    }

    public MarcaDTO updateMarca(Long id, MarcaDTO marcaDTO) {
        Optional<Marca> existingMarca = marcaRepository.findById(id);
        if (existingMarca.isPresent()) {
            Marca marca = existingMarca.get();
            marca.setNombre(marcaDTO.getNombre());
            marca.setDescripcion(marcaDTO.getDescripcion());
            marcaRepository.save(marca);
            return marcaMapper.toDto(marca);
        }
        return null; // Manejo de error si la Marca no existe
    }

     // Método para "eliminar" (ocultar) una marca
    public void eliminarMarca(Long id) {
        Marca marca = marcaRepository.findById(id)
                                     .orElseThrow(() -> new EntityNotFoundException("Marca no encontrada"));
        marca.setActivo(false);
        marcaRepository.save(marca);
    }

    // Método para restaurar una marca (marcar como activa)
    public void restaurarMarca(Long id) {
        Marca marca = marcaRepository.findById(id)
                                     .orElseThrow(() -> new EntityNotFoundException("Marca no encontrada"));
        marca.setActivo(true);
        marcaRepository.save(marca);
    }

    public List<Marca> obtenerMarcasActivas() {
        return marcaRepository.findByActivoTrue();
    }
}

