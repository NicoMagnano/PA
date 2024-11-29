package com.example.pa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Importa el repositorio 'CategoriaRepository', que maneja la interacción con la base de datos

import com.example.pa.controller.DTO.CategoriaDTO.CategoriaDTO; // Importa la anotación '@Autowired' para la inyección de dependencias.
import com.example.pa.controller.Mapper.CategoriaMapper; // Importa la anotación '@Service', que indica que esta clase es un servicio de la aplicación.
import com.example.pa.model.Categoria;
import com.example.pa.repository.CategoriaRepository;

@Service // Anota esta clase como un servicio, indicando que contiene la lógica de negocio. Spring la gestionará como un bean.
public class CategoriaService {

    @Autowired // Inyecta automáticamente el repositorio 'CategoriaRepository' en esta clase para poder usar sus métodos de acceso a la base de datos.
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;
    

    // Creacion de Nueva Categoria
     public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(categoria);
    }

    // Actilizacion de Categorias
    public CategoriaDTO actualizarCategoria(Long id, CategoriaDTO categoriaDTO) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if (categoriaOpt.isPresent()) {
            Categoria categoria = categoriaOpt.get();
            categoria.setNombre(categoriaDTO.getNombre());
            categoriaRepository.save(categoria);
            return categoriaMapper.toDTO(categoria);
        }
        return null;
    }

    // Eliminacion de una Categoría (Cambio de estado "Inactiva")
    public void eliminarCategoria(Long id) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if (categoriaOpt.isPresent()) {
            Categoria categoria = categoriaOpt.get();
            categoria.setActivo(false);
            categoriaRepository.save(categoria);
        }
    }
    // Recuperacion de Categoria (Cambio de estado "Activa")
    public void recuperarCategoria(Long id) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if (categoriaOpt.isPresent()) {
            Categoria categoria = categoriaOpt.get();
            categoria.setActivo(false);
            categoriaRepository.save(categoria);
        }
    }

    public List<CategoriaDTO> obtenerCategorias() {
        List<Categoria> categorias = categoriaRepository.findByActivoFalse();
        return categorias.stream().map(categoriaMapper::toDTO).toList();
    }

}

