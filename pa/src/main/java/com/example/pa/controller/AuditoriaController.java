package com.example.pa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.pa.model.Auditoria;
import com.example.pa.service.AuditoriaService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    // Endpoint para obtener todos los registros de auditoría
    @GetMapping
    public List<Auditoria> obtenerRegistros() {
        return auditoriaService.obtenerRegistros();
    }

  // Endpoint para obtener registros de auditoría por rango de fechas
    @GetMapping("/rango-fechas")
    public List<Auditoria> obtenerRegistrosPorRangoFechas(
        @RequestParam("fechaInicio") String fechaInicio,
        @RequestParam("fechaFin") String fechaFin
    ) {
        // Convertir los parámetros de cadena a LocalDateTime
        LocalDateTime inicio = LocalDateTime.parse(fechaInicio);
        LocalDateTime fin = LocalDateTime.parse(fechaFin);

        return auditoriaService.obtenerRegistrosPorRangoFechas(inicio, fin);
    }

    // Endpoint para obtener registros por usuario
    @GetMapping("/usuario/{username}")
    public List<Auditoria> obtenerRegistrosPorUsuario(@PathVariable String username) {
        return auditoriaService.obtenerRegistrosPorUsuario(username);
    }
}

