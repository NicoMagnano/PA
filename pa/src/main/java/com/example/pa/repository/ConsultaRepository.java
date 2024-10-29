package com.example.pa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pa.model.Consulta;


public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @SuppressWarnings("null")
    List<Consulta> findAll();

    @SuppressWarnings("null")
    Consulta save (Consulta consulta);



}
