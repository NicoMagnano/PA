package com.example.pa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pa.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    //Listado solo productos activos
    List<Producto> findByActivoTrue();
    
    //Listados solo productos inactivos
    List<Producto> findByActivoFalse();
}
