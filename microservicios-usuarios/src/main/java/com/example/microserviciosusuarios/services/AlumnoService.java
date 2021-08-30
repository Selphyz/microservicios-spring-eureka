package com.example.microserviciosusuarios.services;

import com.example.microservicioscommonentities.models.entity.Alumno;
import com.example.microservicioscomunes.services.CommonService;

import java.util.List;

public interface AlumnoService extends CommonService<Alumno> {
    public List<Alumno> findByNombreOrApellido(String term);
}
