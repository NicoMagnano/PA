package com.example.pa.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pa.model.Auditoria;
import com.example.pa.repository.AuditoriaRepository;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    public void registrarEvento(String usuario, String accion, String detalles) {
        Auditoria auditoria = new Auditoria();
        auditoria.setUsuario(usuario);
        auditoria.setAccion(accion);
        auditoria.setDetalles(detalles);
        auditoria.setFecha(LocalDateTime.now());

        auditoriaRepository.save(auditoria);
    }
}

