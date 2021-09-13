package com.example.microservicioscursos.clients;

import com.example.microserviciosusuarios.models.entity.Alumno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "microservicio-usuarios")
public interface AlumnoFeignClient {

    @GetMapping("/alumnos-por-curso")
    public List<Alumno> obtenerAlumnosPorCurso(@RequestParam List<Long> ids);
}
