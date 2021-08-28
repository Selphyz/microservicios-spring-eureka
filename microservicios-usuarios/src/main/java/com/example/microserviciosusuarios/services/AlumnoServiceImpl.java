package com.example.microserviciosusuarios.services;

import com.example.microservicioscomunes.services.CommonServiceImpl;
import com.example.microserviciosusuarios.models.entity.Alumno;
import com.example.microserviciosusuarios.models.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService{

}
