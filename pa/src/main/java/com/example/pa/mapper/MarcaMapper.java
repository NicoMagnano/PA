package com.example.pa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.pa.model.Marca;
import com.example.pa.dto.marca.MarcaDTO;

@Mapper
public interface MarcaMapper {

    MarcaMapper INSTANCE = Mappers.getMapper(MarcaMapper.class);

    // Mapea Marca a MarcaDTO
    MarcaDTO toDto(Marca marca);

    // Mapea MarcaDTO a Marca
    Marca toEntity(MarcaDTO marcaDTO);
}

