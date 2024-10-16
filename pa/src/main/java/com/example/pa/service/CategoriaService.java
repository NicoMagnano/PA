package com.example.pa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; // Importa el repositorio 'CategoriaRepository', que maneja la interacción con la base de datos
import org.springframework.stereotype.Service; // Importa la anotación '@Autowired' para la inyección de dependencias.

import com.example.pa.model.Categoria; // Importa la anotación '@Service', que indica que esta clase es un servicio de la aplicación.
import com.example.pa.repository.CategoriaRepository;

@Service // Anota esta clase como un servicio, indicando que contiene la lógica de negocio. Spring la gestionará como un bean.
public class CategoriaService {

    @Autowired // Inyecta automáticamente el repositorio 'CategoriaRepository' en esta clase para poder usar sus métodos de acceso a la base de datos.
    private CategoriaRepository categoriaRepository;


    // Obtener todas las categorías
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    // Obtener una categoría por ID
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }


    public void eliminarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        categoria.setActivo(false); // Oculta la marca
        categoriaRepository.save(categoria);
    }

    public void recuperarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        categoria.setActivo(true); // Recupera la Cat
        categoriaRepository.save(categoria);
    }

    public List<Categoria> obtenerCategoriasActivas() {
        return categoriaRepository.findByActivo(true);
    }
}