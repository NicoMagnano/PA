package com.example.pa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pa.model.Auditoria;
import com.example.pa.repository.AuditoriaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

      // Método para registrar un evento de auditoría
      public void registrarAuditoria(String username, String accion, String detalles) {
        Auditoria auditoria = new Auditoria();
        auditoria.setUsername(username);
        auditoria.setAccion(accion);
        auditoria.setTimestamp(LocalDateTime.now());
        auditoria.setDetalles(detalles);
        auditoriaRepository.save(auditoria);
    }

    // Método para obtener registros de auditoría por rango de fechas
    public List<Auditoria> obtenerRegistrosPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) 
    {
        return auditoriaRepository.findByTimestampBetween(fechaInicio, fechaFin);
    }

    
    // Método para obtener todos los registros de auditoría
    public List<Auditoria> obtenerRegistros()
    {
    return auditoriaRepository.findAll();
    }

    // Método para obtener registros de auditoría por usuario
    public List<Auditoria> obtenerRegistrosPorUsuario(String username) 
    {
    return auditoriaRepository.findByUsername(username);
    }
}


