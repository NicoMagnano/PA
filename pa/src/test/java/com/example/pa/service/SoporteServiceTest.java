package com.example.pa.service;

import com.example.pa.model.Consulta;
import com.example.pa.model.EstadoConsulta;
import com.example.pa.repository.ConsultaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SoporteServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private SoporteService soporteService;

    private Consulta consulta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        consulta = new Consulta();
        consulta.setId(1L);
        consulta.setEstado(EstadoConsulta.PENDIENTE);

        // Configuración para asegurar que `save` retorna la instancia actualizada
        when(consultaRepository.save(consulta)).thenReturn(consulta);
    }

    // 1. Cambiar estado de "Pendiente" a "En Proceso"
    @Test
    void cambiarEstadoDePendienteAEnProceso() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        Consulta actualizada = soporteService.cambiarEstadoConsulta(1L, EstadoConsulta.EN_PROCESO);

        assertEquals(EstadoConsulta.EN_PROCESO, actualizada.getEstado());
        verify(consultaRepository).save(consulta);
    }

    // 2. Cambiar estado de "En Proceso" a "Resuelta"
    @Test
    void cambiarEstadoDeEnProcesoAResuelta() {
        consulta.setEstado(EstadoConsulta.EN_PROCESO);
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        Consulta actualizada = soporteService.cambiarEstadoConsulta(1L, EstadoConsulta.RESUELTA);

        assertEquals(EstadoConsulta.RESUELTA, actualizada.getEstado());
        verify(consultaRepository).save(consulta);
    }

    // 3. Intentar cambiar estado de "Resuelta" a "Pendiente" (debe fallar)
    @Test
    void revertirEstadoDeResueltaAPendienteDebeFallar() {
        consulta.setEstado(EstadoConsulta.RESUELTA);
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        Exception exception = assertThrows(IllegalStateException.class, () ->
            soporteService.cambiarEstadoConsulta(1L, EstadoConsulta.PENDIENTE)
        );

        assertEquals("No se puede revertir a estado Pendiente", exception.getMessage());
        verify(consultaRepository, never()).save(consulta);
    }

    // 4. Confirmar estado de consulta resuelta
    @Test
    void confirmarEstadoDeConsultaResuelta() {
        consulta.setEstado(EstadoConsulta.RESUELTA);
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        Consulta consultaEncontrada = soporteService.cambiarEstadoConsulta(1L, EstadoConsulta.RESUELTA);

        assertEquals(EstadoConsulta.RESUELTA, consultaEncontrada.getEstado());
        verify(consultaRepository, never()).save(consulta);  // Confirmación sin necesidad de guardar
    }

    // 5. Cambiar estado de "Pendiente" a "Resuelta" directamente
    @Test
    void cambiarEstadoDePendienteAResueltaDirectamente() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        Consulta actualizada = soporteService.cambiarEstadoConsulta(1L, EstadoConsulta.RESUELTA);

        assertEquals(EstadoConsulta.RESUELTA, actualizada.getEstado());
        verify(consultaRepository).save(consulta);
    }
}
