package com.aluracursos.challenge_foro.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record ActualizarTopico(
//        @NotNull
//        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso){
}
