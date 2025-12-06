package com.neill.copagolperu.application.dto.response;

import com.neill.copagolperu.domain.model.Categoria;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record EquipoResponse(
        UUID id,
        Categoria categoria,
        String colorCamiseta,
        int totalJugadores,
        LocalDate fechaRegistro,
        LocalDate fechaActualizacion,
        Boolean activo,

        UUID academiaId,
        String nombreAcademia,
        String logoUrlAcademia,

        UUID entrenadorId,
        String dniEntrenador,
        String apellidosEntrenador,
        String nombresEntrenador,
        String telefonoEntrenador,
        String fotoUrlEntrenador,

        UUID delegadoId,
        String dniDelegado,
        String apellidosDelegado,
        String nombresDelegado,
        String telefonoDelegado,
        String fotoUrlDelegado,

        List<DatosJugador> jugadores
) {
    public record DatosJugador(
            UUID id,
            String apellidos,
            String nombres,
            String dni
    ) {}
}