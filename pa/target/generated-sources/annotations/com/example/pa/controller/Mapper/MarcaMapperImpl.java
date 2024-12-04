package com.example.pa.controller.Mapper;

import com.example.pa.controller.DTO.MarcaDTO.MarcaDTO;
import com.example.pa.model.Marca;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-29T05:27:16-0300",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class MarcaMapperImpl implements MarcaMapper {

    @Override
    public MarcaDTO toDTO(Marca marca) {
        if ( marca == null ) {
            return null;
        }

        Long id = null;
        String nombre = null;
        String descripcion = null;
        boolean activo = false;

        id = marca.getId();
        nombre = marca.getNombre();
        descripcion = marca.getDescripcion();
        activo = marca.isActivo();

        MarcaDTO marcaDTO = new MarcaDTO( id, nombre, descripcion, activo );

        return marcaDTO;
    }

    @Override
    public Marca toEntity(MarcaDTO marcaDTO) {
        if ( marcaDTO == null ) {
            return null;
        }

        Long id = null;
        String nombre = null;
        String descripcion = null;

        id = marcaDTO.getId();
        nombre = marcaDTO.getNombre();
        descripcion = marcaDTO.getDescripcion();

        boolean activo = false;

        Marca marca = new Marca( id, nombre, descripcion, activo );

        return marca;
    }
}
