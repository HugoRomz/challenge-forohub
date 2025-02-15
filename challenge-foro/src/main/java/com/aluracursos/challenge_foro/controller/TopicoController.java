package com.aluracursos.challenge_foro.controller;

import com.aluracursos.challenge_foro.domain.topico.*;
import com.aluracursos.challenge_foro.repository.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    // Create a new topic
    @PostMapping
    public ResponseEntity<ResponseTopico> registrarTopico(
            @RequestBody @Valid RegistrarTopico dtoRegistrarTopico,
            UriComponentsBuilder uriComponentsBuilder) {
        // Save the topic
        var topico = topicoRepository.save(new Topico(dtoRegistrarTopico));

        // Build the response DTO
        var dtoResponseTopico = new ResponseTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getAutor(), topico.getCurso());

        // Generate the resource URI
        URI location = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        return ResponseEntity.created(location).body(dtoResponseTopico);
    }

    // List all topics
    @GetMapping
    public ResponseEntity<List<ListarTopicos>> listarTopicos() {
        // Refactored to return a `ResponseEntity` for consistency
        var topicsList = topicoRepository.findAll()
                .stream()
                .map(ListarTopicos::new)
                .toList();
        return ResponseEntity.ok(topicsList);
    }

    // Get a single topic by ID
    @GetMapping("/{id}")
    public ResponseEntity<ListarTopicos> muestraTopico(@PathVariable Long id) {
        // Explicitly check if the topic exists
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }

        var topicoDto = new ListarTopicos(topicoOptional.get());
        return ResponseEntity.ok(topicoDto);
    }

    // Update an existing topic
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ListarTopicos> actualizaTopico(
            @RequestBody @Valid ActualizarTopico dtoActualizarTopico,
            @PathVariable Long id) {
        // Validate if the topic exists
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }

        var topico = topicoOptional.get();
        topico.actualizarDatos(dtoActualizarTopico); // Update the topic

        return ResponseEntity.ok(new ListarTopicos(topico)); // Return the updated topic DTO
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        System.out.println("DELETE Request received for ID: " + id);
        // Check if the topic exists
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            System.out.println("Topic not found for ID: " + id);
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }

        System.out.println("Deleting topic with ID: " + id);
        topicoRepository.delete(topicoOptional.get()); // Delete the topic
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }
}
