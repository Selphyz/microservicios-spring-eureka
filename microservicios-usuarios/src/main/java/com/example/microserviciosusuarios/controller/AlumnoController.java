package com.example.microserviciosusuarios.controller;

import com.example.microservicioscommonentities.models.entity.Alumno;
import com.example.microservicioscomunes.controllers.CommonController;
import com.example.microserviciosusuarios.services.AlumnoService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term){
        return ResponseEntity.ok(service.findByNombreOrApellido(term));
    }

    @GetMapping("/uploads/img/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id){
        Optional<Alumno> o = service.findById(id);
        if(o.isEmpty() || o.get().getFoto()==null){
            return ResponseEntity.notFound().build();
        }
        Resource imagen = new ByteArrayResource(o.get().getFoto());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarAlumno(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable("id") Long id){
        if(result.hasErrors()){return this.validar(result);}
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

    @PostMapping("/crear-con-foto")
    public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult result,
                                          @RequestParam MultipartFile archivo) throws IOException {
        if(!archivo.isEmpty()){
            alumno.setFoto(archivo.getBytes());
        }
        return super.crear(alumno, result);
    }
    @PutMapping("editar-con-foto/{id}")
    public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result,
                                           @PathVariable("id") Long id, @RequestParam MultipartFile archivo) throws IOException {
        if(result.hasErrors()){return this.validar(result);}
        Optional<Alumno> a = service.findById(id);
        if(a.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Alumno alumnoDB = a.get();
        alumnoDB.setNombre(alumno.getNombre());
        alumnoDB.setApellido(alumno.getApellido());
        alumnoDB.setEmail(alumno.getEmail());
        if(!archivo.isEmpty()){
            alumnoDB.setFoto(archivo.getBytes());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
    }
}
