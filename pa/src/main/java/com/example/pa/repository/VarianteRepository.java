package com.example.pa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pa.model.Variante;

public interface VarianteRepository extends JpaRepository<Variante, Long> {

    //Listado de Variantes Activas
    List<Variante> findByActivoTrue();
    
    // Listado de Variantes Inactivas
    List<Variante> findByActivoFalse();
}