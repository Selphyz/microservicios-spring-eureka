package com.example.microserviciosaxemenes.services;

import com.example.microserviciosaxemenes.models.repository.ExamenRepository;
import com.example.microservicioscommonentities.models.entity.Examen;
import com.example.microservicioscomunes.services.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService{
    @Override
    @Transactional(readOnly = true)
    public List<Examen> findByNombre(String term) {
        return repository.findByNombre(term);
    }
}
