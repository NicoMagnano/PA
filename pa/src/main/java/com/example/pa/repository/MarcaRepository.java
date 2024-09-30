package com.example.pa.repository;

<<<<<<< HEAD

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
=======
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


>>>>>>> 5fde2b6b40d5f6bb0f930384854414f96cc2cae0
import com.example.pa.model.Marca;
import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
<<<<<<< HEAD

    
    List<Marca> findByActivoTrue();

=======
   //Categorías Activas
   List<Marca> findByActivoTrue();
    
   //Categorías Inactivas
   List<Marca> findByActivoFalse();
>>>>>>> 5fde2b6b40d5f6bb0f930384854414f96cc2cae0
}