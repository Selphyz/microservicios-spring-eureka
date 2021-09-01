package com.example.microserviciosaxemenes.services;

import com.example.microserviciosaxemenes.models.entities.Examen;
import com.example.microserviciosaxemenes.models.repository.ExamenRepository;
import com.example.microservicioscomunes.services.CommonServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService{
}
