package org.educavagas.api.controller;

import org.educavagas.api.dto.EscolaDto;
import org.educavagas.api.mapper.EscolaMapper;
import org.educavagas.api.model.Escola;
import org.educavagas.api.repository.EscolaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/escolas")
public class EscolaController {

    private final EscolaRepository repo;
    private final EscolaMapper     mapper;

    public EscolaController(EscolaRepository repo, EscolaMapper mapper) {
        this.repo   = repo;
        this.mapper = mapper;
    }

    @GetMapping
    public List<EscolaDto> listar() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscolaDto> porId(@PathVariable UUID id) {
        return repo.findById(id)
                .map(e -> ResponseEntity.ok(mapper.toDto(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EscolaDto> criar(@RequestBody EscolaDto dto) {
        Escola e = mapper.toEntity(dto);
        e = repo.save(e);
        return ResponseEntity
                .created(URI.create("/escolas/" + e.getUuid()))
                .body(mapper.toDto(e));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscolaDto> atualizar(
            @PathVariable UUID id,
            @RequestBody EscolaDto dto
    ) {
        return repo.findById(id)
                .map(existing -> {
                    Escola e = mapper.toEntity(dto);
                    e.setUuid(id);
                    return mapper.toDto(repo.save(e));
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}