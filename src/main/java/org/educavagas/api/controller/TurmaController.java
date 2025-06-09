package org.educavagas.api.controller;

import org.educavagas.api.dto.TurmaDto;
import org.educavagas.api.service.TurmaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    private final TurmaService service;

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    @GetMapping
    public List<TurmaDto> all() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TurmaDto> create(@RequestBody TurmaDto dto) {
        var t = service.create(dto);
        return ResponseEntity.created(URI.create("/turmas/" + t.uuid()))
                .body(t);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaDto> update(
            @PathVariable UUID id,
            @RequestBody TurmaDto dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}