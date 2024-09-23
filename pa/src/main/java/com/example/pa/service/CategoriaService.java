package com.example.pa.service;

//Creo el Servicio CategoriaService
//El servicio manejará la lógica de negocio. Podemos utilizar el repositorio para realizar las operaciones de base de datos. 
//Creo una clase en el paquete com.example.pa.service.

import com.example.pa.entity.Categoria; // Importa la clase 'Categoria', que es la entidad que representa las categorías en la aplicación.
import com.example.pa.repository.CategoriaRepository; // Importa el repositorio 'CategoriaRepository', que maneja la interacción con la base de datos
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación '@Autowired' para la inyección de dependencias.
import org.springframework.stereotype.Service; // Importa la anotación '@Service', que indica que esta clase es un servicio de la aplicación.

import java.util.List;
import java.util.Optional;

@Service // Anota esta clase como un servicio, indicando que contiene la lógica de negocio. Spring la gestionará como un bean.
public class CategoriaService {

    @Autowired // Inyecta automáticamente el repositorio 'CategoriaRepository' en esta clase para poder usar sus métodos de acceso a la base de datos.
    private CategoriaRepository categoriaRepository;

    //Listado de categorías Activas
    public List<Categoria> listarCategoriasActivas() {
        return categoriaRepository.findByActivoTrue();
    }

    // Método para obtener una categoría específica por su ID. Devuelve un Optional que puede contener o no la categoría.
    public Optional<Categoria> obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    // Creacion de Nueva Categoria
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Actilizacion de Categorias
    public Categoria actualizarCategoria(Long id, Categoria categoriaActualizada) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoria.setNombre(categoriaActualizada.getNombre());
            categoria.setActivo(categoriaActualizada.isActivo());
            return categoriaRepository.save(categoria);
        } else {
            throw new RuntimeException("Categoría no encontrada");
        }
    }

    // Eliminacion de una Categoría (Cambio de estado "Inactiva")
    public void eliminarCategoria(Long id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoria.setActivo(false);  // Cambia estado a inactivo
            categoriaRepository.save(categoria);
        } else {
            throw new RuntimeException("Categoría no encontrada");
        }
    }

    // Recuperacion de Categoria (Cambio de estado "Activa")
    public Categoria recuperarCategoria(Long id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoria.setActivo(true);  // Cambia estado a activo
            return categoriaRepository.save(categoria);
        } else {
            throw new RuntimeException("Categoría no encontrada");
        }
    }

}

