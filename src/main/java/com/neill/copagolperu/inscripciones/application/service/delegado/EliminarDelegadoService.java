package com.neill.copagolperu.inscripciones.application.service.delegado;

import com.neill.copagolperu.inscripciones.domain.model.Equipo;
import com.neill.copagolperu.inscripciones.domain.repository.DelegadoRepository;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EliminarDelegadoService {
    private final DelegadoRepository delegadoRepository;
    private final EquipoRepository equipoRepository;

    @Transactional
    public void eliminarDelegado(UUID delegadoId) {
        List<Equipo> equipos = equipoRepository.findByDelegadoId(delegadoId);

        for (Equipo equipo : equipos) {
            equipo.setDelegado(null);
        }

        delegadoRepository.deleteById(delegadoId);
    }

}
