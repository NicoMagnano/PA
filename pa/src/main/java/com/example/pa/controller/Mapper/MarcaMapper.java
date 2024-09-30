package com.example.pa.controller.Mapper;

import org.mapstruct.Mapper;

import com.example.pa.controller.DTO.MarcaDTO.MarcaDTO;
import com.example.pa.model.Marca;

@Mapper
public interface MarcaMapper {
    MarcaDTO toDTO(Marca marca);
    Marca toEntity(MarcaDTO marcaDTO);
}