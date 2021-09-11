package com.example.microservicioscursos.service;

import com.example.microservicioscomunes.services.CommonService;
import com.example.microservicioscursos.models.entity.Curso;

public interface CursoService extends CommonService<Curso> {
    Curso findCursoByAlumnoId(Long id);
    public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
}
