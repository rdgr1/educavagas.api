package org.educavagas.api.controller;

import org.educavagas.api.dto.VagaDto;
import org.educavagas.api.service.VagaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vagas")
public class VagaController {
    private final VagaService service;

    public VagaController(VagaService service) {
        this.service = service;
    }

    @GetMapping
    public List<VagaDto> all() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<VagaDto> create(@RequestBody VagaDto dto) {
        var v = service.create(dto);
        return ResponseEntity.created(URI.create("/vagas/" + v.uuid()))
                .body(v);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VagaDto> update(
            @PathVariable UUID id,
            @RequestBody VagaDto dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}