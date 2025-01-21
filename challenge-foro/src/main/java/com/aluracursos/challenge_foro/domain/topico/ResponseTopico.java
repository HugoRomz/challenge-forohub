package com.aluracursos.challenge_foro.domain.topico;

public record ResponseTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso) {
}
