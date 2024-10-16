package com.example.pa.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pa.model.Compra;
import com.example.pa.repository.CompraRepository;


@Service


 
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;
    
    public  List<Compra> findAll() {
        return compraRepository.findAll();
    }
    
}
