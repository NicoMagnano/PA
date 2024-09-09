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

    // Método para listar todas las categorías almacenadas en la base de datos
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // Método para obtener una categoría específica por su ID. Devuelve un Optional que puede contener o no la categoría.
    public Optional<Categoria> obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    // Método para guardar una nueva categoría o actualizar una existente en la base de datos.
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Método para eliminar una categoría de la base de datos por su ID.
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    // Método para actualizar una categoría existente en la base de datos.
    public Categoria actualizarCategoria(Long id, Categoria categoriaActualizada) {
        return categoriaRepository.findById(id) // Busca la categoría por su ID.
                .map(categoria -> { // Si la categoría existe, actualiza sus campos.
                    categoria.setNombre(categoriaActualizada.getNombre()); // Actualiza el nombre de la categoría con el valor proporcionado.
                    return categoriaRepository.save(categoria); // Guarda los cambios en la base de datos.
                })
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id)); // Si la categoría no se encuentra, lanza una excepción.
    }
}

