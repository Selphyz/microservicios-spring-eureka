package com.example.microserviciosaxemenes.controllers;

import com.example.microserviciosaxemenes.services.ExamenService;
import com.example.microservicioscommonentities.models.entity.Examen;
import com.example.microservicioscommonentities.models.entity.Pregunta;
import com.example.microservicioscomunes.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){return this.validar(result);}
        Optional<Examen> o = service.findById(id);
        if(o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Examen examenDb = o.get();
        examenDb.setNombre(examen.getNombre());
        List<Pregunta> eliminadas = new ArrayList<>();
        examenDb.getPreguntas().forEach(pdb->{
            if(!examen.getPreguntas().contains(pdb)){
                eliminadas.add(pdb);
            }
        });
        eliminadas.forEach(examenDb::removePregunta);
        examenDb.setPreguntas(examen.getPreguntas());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
    }
    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term){
        return ResponseEntity.ok(service.findByNombre(term));
    }
    @GetMapping("/asignaturas")
    public ResponseEntity<?> listAsignaturas(){
        return ResponseEntity.ok(service.findAllAsignaturas());
    }
}
