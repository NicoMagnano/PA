package com.example.pa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.pa.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
   //Categorías Activas
   List<Marca> findByActivoTrue();
    
   //Categorías Inactivas
   List<Marca> findByActivoFalse();
}