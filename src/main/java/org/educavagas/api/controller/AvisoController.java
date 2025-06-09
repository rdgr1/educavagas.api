package org.educavagas.api.controller;

import org.educavagas.api.dto.AvisoDto;
import org.educavagas.api.service.AvisoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/avisos")
public class AvisoController {
    private final AvisoService service;

    public AvisoController(AvisoService service) {
        this.service = service;
    }

    @GetMapping
    public List<AvisoDto> all() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvisoDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<AvisoDto> create(@RequestBody AvisoDto dto) {
        var a = service.create(dto);
        return ResponseEntity.created(URI.create("/avisos/" + a.uuid()))
                .body(a);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvisoDto> update(
            @PathVariable UUID id,
            @RequestBody AvisoDto dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}