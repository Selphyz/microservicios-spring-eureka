package com.example.microservicioscursos.service;

import com.example.microservicioscomunes.services.CommonService;
import com.example.microservicioscursos.models.entity.Curso;
import com.example.microserviciosusuarios.models.entity.Alumno;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CursoService extends CommonService<Curso> {
    Curso findCursoByAlumnoId(Long id);
    public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
    List<Alumno> obtenerAlumnosPorCurso(@RequestParam List<Long> ids);
    public void eliminarCursoAlumnosPorId(Long id);
}
