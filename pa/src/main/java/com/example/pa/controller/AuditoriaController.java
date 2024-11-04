package com.example.pa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pa.model.Auditoria;
import com.example.pa.repository.AuditoriaRepository;

@RestController
@RequestMapping("/api/auditoria")
public class AuditoriaController {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @GetMapping
    public List<Auditoria> obtenerRegistros() {
        return auditoriaRepository.findAll();
    }

    // Puedes agregar métodos de búsqueda personalizados aquí
}

