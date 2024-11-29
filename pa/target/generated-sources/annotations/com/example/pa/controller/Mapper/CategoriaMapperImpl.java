package com.example.pa.controller.Mapper;

import javax.annotation.processing.Generated;

import org.springframework.stereotype.Component;

import com.example.pa.controller.DTO.CategoriaDTO.CategoriaDTO;
import com.example.pa.model.Categoria;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-04T19:32:30-0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class CategoriaMapperImpl implements CategoriaMapper {

    @Override
    public CategoriaDTO toDTO(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        Long id = null;
        String nombre = null;
        boolean activo = false;

        id = categoria.getId();
        nombre = categoria.getNombre();
        activo = categoria.isActivo();

        CategoriaDTO categoriaDTO = new CategoriaDTO( id, nombre, activo );

        return categoriaDTO;
    }

    @Override
    public Categoria toEntity(CategoriaDTO categoriaDTO) {
        if ( categoriaDTO == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setId( categoriaDTO.getId() );
        categoria.setNombre( categoriaDTO.getNombre() );
        categoria.setActivo( categoriaDTO.isActivo() );

        return categoria;
    }
}
