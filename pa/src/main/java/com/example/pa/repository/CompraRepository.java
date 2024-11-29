package com.example.pa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pa.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    
}
