package com.neill.copagolperu.application.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ApoderadoResponse(
        UUID id,
        String dni,
        String apellidos,
        String nombres,
        LocalDate fechaNacimiento,
        String parentesco,
        String telefono,

        List<EquipoResponse.DatosJugador> jugadores
) {}