package com.example.microservicioscomunes.controllers;

import com.example.microservicioscomunes.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CommonController<E, S extends CommonService<E>> {
    @Autowired
    protected S service;

    @GetMapping()
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarUno(@PathVariable("id") Long id){
        Optional<E> a = service.findById(id);
        if(a.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(a.get());
    }
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody E entity){
        E entityDB = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDB);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
