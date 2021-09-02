package com.example.microserviciosaxemenes.services;

import com.example.microservicioscommonentities.models.entity.Asignatura;
import com.example.microservicioscommonentities.models.entity.Examen;
import com.example.microservicioscomunes.services.CommonService;

import java.util.List;

public interface ExamenService extends CommonService<Examen> {
    public List<Examen> findByNombre(String term);
    public Iterable<Asignatura> findAllAsignaturas();
}
