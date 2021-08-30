package com.example.microserviciosusuarios.controller;

import com.example.microservicioscommonentities.models.entity.Alumno;
import com.example.microservicioscomunes.controllers.CommonController;
import com.example.microservicioscommonentities.models.entity.Alumno;
import com.example.microserviciosusuarios.services.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editarAlumno(@RequestBody Alumno alumno, @PathVariable("id") Long id){
        Optional<Alumno> a = service.findById(id);
        if(a.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Alumno alumnoDB = a.get();
        alumnoDB.setNombre(alumno.getNombre());
        alumnoDB.setApellido(alumno.getApellido());
        alumnoDB.setEmail(alumno.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term){
        return ResponseEntity.ok(service.findByNombreOrApellido(term));
    }
}
