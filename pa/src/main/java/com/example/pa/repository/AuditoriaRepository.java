package com.example.pa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pa.model.Auditoria;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

    // Método para buscar registros entre dos fechas (útil para auditoría)
    List<Auditoria> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    
    // Método para buscar registros por username
    List<Auditoria> findByUsername(String username);

    
    // Otros métodos según lo necesites (por ejemplo, por acción, por IP, etc.)
}


