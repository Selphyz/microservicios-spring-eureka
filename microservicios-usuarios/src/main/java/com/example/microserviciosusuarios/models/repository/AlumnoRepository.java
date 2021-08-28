package com.example.microserviciosusuarios.models.repository;

import com.example.microserviciosusuarios.models.entity.Alumno;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
}
