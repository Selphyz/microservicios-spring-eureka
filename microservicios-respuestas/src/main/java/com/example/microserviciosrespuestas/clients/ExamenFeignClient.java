package com.example.microserviciosrespuestas.clients;

import com.example.microservicioscommonentities.models.entity.Examen;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-examenes")
public interface ExamenFeignClient {
    @GetMapping("/{id}")
    public Examen obtenerExamenPorId(@PathVariable Long id);
}
