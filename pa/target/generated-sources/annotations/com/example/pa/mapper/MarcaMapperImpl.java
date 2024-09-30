package com.example.pa.mapper;

import com.example.pa.dto.marca.MarcaDTO;
import com.example.pa.model.Marca;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-30T18:44:44-0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.40.0.v20240919-1711, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@Component
public class MarcaMapperImpl implements MarcaMapper {

    @Override
    public MarcaDTO toDto(Marca marca) {
        if ( marca == null ) {
            return null;
        }

        MarcaDTO marcaDTO = new MarcaDTO();

        marcaDTO.setId( marca.getId() );
        marcaDTO.setNombre( marca.getNombre() );
        marcaDTO.setDescripcion( marca.getDescripcion() );

        return marcaDTO;
    }

    @Override
    public Marca toEntity(MarcaDTO marcaDTO) {
        if ( marcaDTO == null ) {
            return null;
        }

        String descripcion = null;
        Long id = null;
        String nombre = null;

        descripcion = marcaDTO.getDescripcion();
        id = marcaDTO.getId();
        nombre = marcaDTO.getNombre();

        boolean activo = false;

        Marca marca = new Marca( id, nombre, descripcion, activo );

        return marca;
    }
}
