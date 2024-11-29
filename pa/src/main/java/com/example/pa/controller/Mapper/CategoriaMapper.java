package com.example.pa.controller.Mapper;

import org.mapstruct.Mapper;

import com.example.pa.controller.DTO.CategoriaDTO.CategoriaDTO;
import com.example.pa.model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDTO toDTO(Categoria categoria);
    Categoria toEntity(CategoriaDTO categoriaDTO);
}