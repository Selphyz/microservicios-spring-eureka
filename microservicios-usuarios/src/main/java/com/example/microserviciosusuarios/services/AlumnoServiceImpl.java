package com.example.microserviciosusuarios.services;

import com.example.microservicioscomunes.services.CommonServiceImpl;
import com.example.microserviciosusuarios.client.CursoFeignClient;
import com.example.microserviciosusuarios.models.entity.Alumno;
import com.example.microserviciosusuarios.models.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService{

    @Autowired
    private CursoFeignClient clientCurso;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String term) {
        return repository.findByNombreOrApellido(term);
    }

    @Override
    @Transactional
    public Iterable<Alumno> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void eliminarCursoAlumnoPorId(Long id) {
        clientCurso.eliminarCursoAlumnoPorId(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        super.deleteById(id);
        this.eliminarCursoAlumnoPorId(id);
    }
}
