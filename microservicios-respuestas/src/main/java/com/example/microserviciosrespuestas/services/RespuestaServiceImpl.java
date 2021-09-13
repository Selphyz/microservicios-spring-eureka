package com.example.microserviciosrespuestas.services;

import com.example.microservicioscommonentities.models.entity.Examen;
import com.example.microservicioscommonentities.models.entity.Pregunta;
import com.example.microserviciosrespuestas.clients.ExamenFeignClient;
import com.example.microserviciosrespuestas.models.entities.Respuesta;
import com.example.microserviciosrespuestas.models.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaServiceImpl implements RespuestaService {
    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private ExamenFeignClient examenClient;

    @Override
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
        return repository.saveAll(respuestas);
    }

    @Override
    public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
        Examen examen = examenClient.obtenerExamenPorId(examenId);
        List<Pregunta> preguntas = examen.getPreguntas();
        List<Long> preguntasIds = preguntas.stream().map(Pregunta::getId).collect(Collectors.toList());
        List<Respuesta> respuestas = (List<Respuesta>) repository.findRespuestaByAlumnoByPreguntaId(alumnoId, preguntasIds);
        respuestas = respuestas.stream().peek(r -> preguntas.forEach(p ->{
            if(p.getId()==r.getPreguntaId()){
                r.setPregunta(p);
            }
        })).collect(Collectors.toList());
        return respuestas;
//        return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
    }

    @Override
    public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
        return null;
//        return repository.findExamenesIdsConRespuestasByAlumno(alumnoId);
    }
}
