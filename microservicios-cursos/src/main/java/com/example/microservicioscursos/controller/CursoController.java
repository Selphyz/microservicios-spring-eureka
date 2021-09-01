package com.example.microservicioscursos.controller;

import com.example.microservicioscommonentities.models.entity.Alumno;
import com.example.microservicioscommonentities.models.entity.Examen;
import com.example.microservicioscomunes.controllers.CommonController;
import com.example.microservicioscursos.models.entity.Curso;
import com.example.microservicioscursos.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id){
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();
        dbCurso.setNombre(curso.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }
    @PutMapping("/{id}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id){
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();
//        alumnos.forEach(a -> {
//            dbCurso.addAlumno(a);
//        });
        alumnos.forEach(dbCurso::addAlumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }
    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();
        dbCurso.removeAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }
    @PutMapping("/{id}/asignar-examenes")
    public ResponseEntity<?> asignarExanenes(@RequestBody List<Examen> examen, @PathVariable Long id){
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();
//        alumnos.forEach(a -> {
//            dbCurso.addAlumno(a);
//        });
        examen.forEach(dbCurso::addExamen);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }
    @PutMapping("/{id}/eliminar-examen")
    public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();
        dbCurso.removeExamen(examen);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }
    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
        Curso curso = service.findCursoByAlumnoId(id);
        return ResponseEntity.ok(curso);
    }
}
