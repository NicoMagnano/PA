package com.example.pa.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.pa.controller.DTO.ConsultaDTO.ConsultaDTO;
import com.example.pa.model.Consulta;
import com.example.pa.repository.ConsultaRepository;

@Service
public class SoporteService {
    private final String RUTA_ARCHIVOS = "C:\\Users\\matia\\OneDrive\\Escritorio\\Matias\\la facu\\cuarto año\\programacion avanzada\\PA\\archivosSoporte";

    @Autowired
    private ConsultaRepository consultaRepository;


    private void verificarPermisosDeEscritura() {
        File directorio = new File(RUTA_ARCHIVOS);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        if (!directorio.canWrite()) {
            throw new RuntimeException("No hay permisos de escritura en el directorio: " + RUTA_ARCHIVOS);
        }
    }

    
    public Consulta crearConsulta(ConsultaDTO consultaDTO) throws IOException {
        Consulta consulta = new Consulta();
        consulta.setDescripcion(consultaDTO.getDescripcion());
        consulta.setFechaEnvio(LocalDateTime.now());
        this.verificarPermisosDeEscritura();

        // Guardar archivos y obtener las rutas de los archivos guardados
        List<String> rutasArchivos = guardarArchivos(consultaDTO.getArchivos());
        consulta.setArchivosAdjuntos(rutasArchivos);

        return consultaRepository.save(consulta);
    }

    // Método para guardar archivos adjuntos en el sistema de archivos
    private List<String> guardarArchivos(List<MultipartFile> archivos) throws IOException {
        List<String> rutasGuardadas = new ArrayList<>();
    
        for (MultipartFile archivo : archivos) {
            if (!archivo.isEmpty()) {
                String tipoArchivo = archivo.getContentType();
                
                // Validar que el archivo sea una imagen
                if (!tipoArchivo.equals("image/png") && !tipoArchivo.equals("image/jpeg") && !tipoArchivo.equals("image/jpg")) {
                    throw new IllegalArgumentException("Tipo de archivo no permitido. Solo se aceptan imágenes (PNG, JPEG).");
                }
                
                String nombreArchivo = UUID.randomUUID() + "_" + archivo.getOriginalFilename();
                File archivoDestino = new File(RUTA_ARCHIVOS + nombreArchivo);
                archivo.transferTo(archivoDestino);
                rutasGuardadas.add(nombreArchivo);
            }
        }
    
        return rutasGuardadas;
    }
    

    public List<Consulta> obtenerTodasLasConsultas() {
        return consultaRepository.findAll();
    }
}
