package com.example.microserviciosrespuestas.services;

import com.example.microserviciosrespuestas.models.entities.Respuesta;

public interface RespuestaService {
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);
    public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
    public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);
}
