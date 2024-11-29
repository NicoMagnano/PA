package com.example.pa.service;

import com.example.pa.controller.DTO.MarcaDTO.MarcaDTO;
import com.example.pa.controller.Mapper.MarcaMapper;
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

    @Autowired
    private MarcaMapper marcaMapper;

   public MarcaDTO crearMarca(MarcaDTO marcaDTO) {
        Marca marca = marcaMapper.toEntity(marcaDTO);
        marca = marcaRepository.save(marca);
        return marcaMapper.toDTO(marca);
    }

    public MarcaDTO actualizarMarca(Long id, MarcaDTO marcaDTO) {
        Optional<Marca> marcaOpt = marcaRepository.findById(id);
        if (marcaOpt.isPresent()) {
            Marca marca = marcaOpt.get();
            marca.setNombre(marcaDTO.getNombre());
            marcaRepository.save(marca);
            return marcaMapper.toDTO(marca);
        }
        return null;
    }
   
    public void eliminarMarca(Long id) {
        Optional<Marca> marcaOpt = marcaRepository.findById(id);
        if (marcaOpt.isPresent()) {
            Marca marca = marcaOpt.get();
            marca.setActivo(false);  // Eliminación lógica
            marcaRepository.save(marca);
        }
    }
   
    public void recuperarMarca(Long id) {
        Optional<Marca> marcaOpt = marcaRepository.findById(id);
        if (marcaOpt.isPresent()) {
            Marca marca = marcaOpt.get();
            marca.setActivo(false);  // Recuperación
            marcaRepository.save(marca);
        }
    }

    public List<MarcaDTO> obtenerMarcas() {
        List<Marca> marcas = marcaRepository.findByActivoFalse();
        return marcas.stream()
                     .map(marcaMapper::toDTO)
                     .toList();
    }
}