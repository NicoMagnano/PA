package com.example.pa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pa.model.SubCategoria;


public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Long> {

    // Método para obtener solo subcategorías activas
    List<SubCategoria> findByActivoTrue();
    
    // Método para obtener solo subcategorías inactivas (opcional)
    List<SubCategoria> findByActivoFalse();
}

