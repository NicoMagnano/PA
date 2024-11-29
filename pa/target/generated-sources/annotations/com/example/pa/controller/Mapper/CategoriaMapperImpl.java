package com.example.pa.controller.Mapper;

import com.example.pa.controller.DTO.CategoriaDTO.CategoriaDTO;
import com.example.pa.model.Categoria;
import com.example.pa.model.SubCategoria;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-29T05:27:16-0300",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
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

        Long id = null;
        String nombre = null;
        boolean activo = false;

        id = categoriaDTO.getId();
        nombre = categoriaDTO.getNombre();
        activo = categoriaDTO.isActivo();

        List<SubCategoria> subcategorias = null;

        Categoria categoria = new Categoria( id, nombre, activo, subcategorias );

        return categoria;
    }
}
