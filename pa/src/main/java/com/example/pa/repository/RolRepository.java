package com.example.pa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pa.model.Rol;
import com.example.pa.model.RolNombre;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(RolNombre nombre);
}

