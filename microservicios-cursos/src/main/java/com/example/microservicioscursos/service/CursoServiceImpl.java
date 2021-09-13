package com.example.microservicioscursos.service;

import com.example.microservicioscomunes.services.CommonServiceImpl;
import com.example.microservicioscursos.clients.AlumnoFeignClient;
import com.example.microservicioscursos.clients.RespuestaFeignClient;
import com.example.microservicioscursos.models.entity.Curso;
import com.example.microservicioscursos.models.repository.CursoRepository;
import com.example.microserviciosusuarios.models.entity.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

    @Autowired
    private RespuestaFeignClient client;

    @Autowired
    private AlumnoFeignClient clientAlumno;

    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByAlumnoId(Long id) {
        return repository.findCursoByAlumnoId(id);
    }

    @Override
    public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
        return client.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
    }

    @Override
    public List<Alumno> obtenerAlumnosPorCurso(List<Long> ids) {
        return clientAlumno.obtenerAlumnosPorCurso(ids);
    }

    @Override
    @Transactional
    public void eliminarCursoAlumnosPorId(Long id) {
        repository.eliminarCursoAlumnoPorId(id);
    }
}
