package com.neill.copagolperu.application.dto.response;

import com.neill.copagolperu.domain.model.Categoria;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record EquipoResponse(
        UUID id,
        Categoria categoria,
        String colorCamiseta,
        LocalDate fechaRegistro,
        LocalDate fechaActualizacion,
        Boolean activo,

        UUID academiaId,
        String nombreAcademia,
        String logoUrlAcademia,

        UUID entrenadorId,
        String apellidosEntrenador,
        String nombresEntrenador,

        UUID delegadoId,
        String apellidosDelegado,
        String nombresDelegado,

        List<DatosJugador> jugadores
) {
    public record DatosJugador(
            UUID id,
            String apellidos,
            String nombres,
            String dni
    ) {}
}