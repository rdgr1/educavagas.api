package org.educavagas.api.controller;

import org.educavagas.api.dto.SerieDto;
import org.educavagas.api.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/series")
public class SerieController {
    private final SerieService service;

    public SerieController(SerieService service) {
        this.service = service;
    }

    @GetMapping
    public List<SerieDto> all() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SerieDto> create(@RequestBody SerieDto dto) {
        var s = service.create(dto);
        return ResponseEntity.created(URI.create("/series/" + s.uuid()))
                .body(s);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SerieDto> update(
            @PathVariable UUID id,
            @RequestBody SerieDto dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}