package com.aluracursos.challenge_foro.domain.topico;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_Creacion;
    private Boolean status;
    private String autor;
    private String curso;
    private Integer respuestas;

    public Topico() {

    }

    public Topico(RegistrarTopico dtoRegistrarTopico) {
        this.titulo = dtoRegistrarTopico.titulo();
        this.mensaje = dtoRegistrarTopico.mensaje();
        this.status = true;
        this.autor = dtoRegistrarTopico.autor();
        this.curso = dtoRegistrarTopico.curso();
        this.fecha_Creacion = LocalDateTime.now();
        this.respuestas = 0;
    }

    public ResponseTopico actualizarDatos (ActualizarTopico dtoActualizarTopico) {
        if (dtoActualizarTopico.titulo() != null) {
            this.titulo = dtoActualizarTopico.titulo();
        }
        if (dtoActualizarTopico.mensaje() != null) {
            this.mensaje = dtoActualizarTopico.mensaje();
        }
        if (dtoActualizarTopico.autor() != null) {
            this.autor = dtoActualizarTopico.autor();
        }
        if (dtoActualizarTopico.curso() != null) {
            this.curso = dtoActualizarTopico.curso();
        }
        return new ResponseTopico(this.id, this.titulo, this.mensaje, this.autor, this.curso);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha_Creacion() {
        return fecha_Creacion;
    }

    public void setFecha_Creacion(LocalDateTime fecha_Creacion) {
        this.fecha_Creacion = fecha_Creacion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Integer getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Integer respuestas) {
        this.respuestas = respuestas;
    }
}