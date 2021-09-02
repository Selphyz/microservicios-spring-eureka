package com.example.microservicioscommonentities.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="examenes")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 4, max=30)
    private String nombre;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date createdAt;

    @JsonIgnoreProperties(value = {"examen"}, allowSetters = true)
    @OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pregunta> preguntas;

    @ManyToMany(fetch = FetchType.LAZY)
    private Asignatura asignatura;

    public Examen() {
        this.preguntas = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas.clear();
        preguntas.forEach(this::addPregunta);

    }

    public void addPregunta(Pregunta pregunta) {
        this.preguntas.add(pregunta);
        pregunta.setExamen(this);
    }

    public void removePregunta(Pregunta pregunta) {
        this.preguntas.remove(pregunta);
        pregunta.setExamen(null);
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createAt) {
        this.createdAt = createAt;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof Examen)) {
            return false;
        }

        Examen a = (Examen) obj;

        return this.id != null && this.id.equals(a.getId());
    }
}