package com.example.pa.repository; //// Define el paquete en el que está la clase. En este caso, 'repository' es el paquete donde se ubican las clases que manejan la interacción con la base de datos.

// Defino un repositorio que extienda de JpaRepository para manejar las operaciones de CRUD automáticamente. 
// Creo una interfaz en el paquete com.example.pa.repository:

import com.example.pa.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interfaz es un repositorio Spring, lo que permite que Spring lo descubra automáticamente y lo gestione como un bean.
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Esta interfaz extiende 'JpaRepository', lo que significa que hereda todos los métodos CRUD estándar como save(), findAll(), findById(), deleteById(), etc.
    // 'Categoria' es la entidad que gestionará este repositorio.
    // 'Long' es el tipo de dato del identificador único (id) de la entidad 'Categoria'.
    // No se necesita implementar nada manualmente ya que JpaRepository proporciona automáticamente las operaciones de la base de datos.

    
}
