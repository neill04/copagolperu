package com.neill.copagolperu.shared.application.service.ubicacion;

import com.neill.copagolperu.shared.domain.model.ubicacion.Departamento;
import com.neill.copagolperu.shared.domain.repository.ubicacion.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarDepartamentosService {

    private final DepartamentoRepository departamentoRepository;

    public ListarDepartamentosService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public List<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }
}