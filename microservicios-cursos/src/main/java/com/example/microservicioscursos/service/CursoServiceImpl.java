package com.example.microservicioscursos.service;

import com.example.microservicioscomunes.services.CommonServiceImpl;
import com.example.microservicioscursos.models.entity.Curso;
import com.example.microservicioscursos.models.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByAlumnoId(Long id) {
        return repository.findCursoByAlumnoId(id);
    }
}
