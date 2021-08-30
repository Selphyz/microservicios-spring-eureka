package com.example.microserviciosusuarios.services;

import com.example.microservicioscomunes.services.CommonServiceImpl;
import com.example.microservicioscommonentities.models.entity.Alumno;
import com.example.microserviciosusuarios.models.repository.AlumnoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService{

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String term) {
        return repository.findByNombreOrApellido(term);
    }
}
