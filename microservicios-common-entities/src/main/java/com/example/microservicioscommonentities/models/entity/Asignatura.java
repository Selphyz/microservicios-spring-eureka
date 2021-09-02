package com.example.microservicioscommonentities.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="asignaturas")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @JsonIgnoreProperties(value = {"hijos"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Asignatura padre;

    @JsonIgnoreProperties(value = {"padre"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "padre", cascade = CascadeType.ALL)
    private ArrayList<Asignatura> hijos;

    public Asignatura(Asignatura hijos) {
        this.hijos = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Asignatura getPadre() {
        return padre;
    }

    public void setPadre(Asignatura padre) {
        this.padre = padre;
    }

    public ArrayList<Asignatura> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Asignatura> hijos) {
        this.hijos = hijos;
    }
}