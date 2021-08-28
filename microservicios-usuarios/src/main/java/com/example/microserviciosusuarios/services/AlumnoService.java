package com.example.microserviciosusuarios.services;

import com.example.microservicioscomunes.services.CommonService;
import com.example.microserviciosusuarios.models.entity.Alumno;

import java.util.Optional;

public interface AlumnoService extends CommonService<Alumno> {
    public Iterable<Alumno> findAll();
    public Optional<Alumno> findById(Long id);
    public Alumno save(Alumno alumno);
    public void deleteById(Long id);
}
