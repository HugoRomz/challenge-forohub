package com.aluracursos.challenge_foro.repository;

import com.aluracursos.challenge_foro.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

}