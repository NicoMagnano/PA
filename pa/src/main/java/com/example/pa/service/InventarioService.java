package com.example.pa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pa.entity.Inventario;
import com.example.pa.repository.InventarioRepository;

import java.util.Collections;
import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository InventarioRepository;

    // Método para obtener todo el historial de inventario
    public List<Inventario> obtenerTodosLosInventarios() {
        List<Inventario> inventarios = InventarioRepository.findAll();
        return inventarios != null ? inventarios : Collections.emptyList(); //Garantia No Nula
    }
}