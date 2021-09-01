package com.example.microserviciosaxemenes.controllers;

import com.example.microserviciosaxemenes.models.entities.Examen;
import com.example.microserviciosaxemenes.models.entities.Pregunta;
import com.example.microserviciosaxemenes.services.ExamenService;
import com.example.microservicioscomunes.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, @PathVariable Long id){
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
}
