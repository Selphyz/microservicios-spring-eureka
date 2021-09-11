package com.example.microserviciosusuarios.services;


import com.example.microservicioscomunes.services.CommonService;
import com.example.microserviciosusuarios.models.entity.Alumno;

import java.util.List;

public interface AlumnoService extends CommonService<Alumno> {
    public List<Alumno> findByNombreOrApellido(String term);
}
