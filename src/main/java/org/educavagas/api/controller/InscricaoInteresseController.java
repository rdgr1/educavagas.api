package org.educavagas.api.controller;

import org.educavagas.api.dto.InscricaoInteresseDto;
import org.educavagas.api.services.InscricaoInteresseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoInteresseController {
    private final InscricaoInteresseService service;

    public InscricaoInteresseController(InscricaoInteresseService service) {
        this.service = service;
    }

    @GetMapping
    public List<InscricaoInteresseDto> all() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoInteresseDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<InscricaoInteresseDto> create(
            @RequestBody InscricaoInteresseDto dto
    ) {
        var i = service.create(dto);
        return ResponseEntity.created(URI.create("/inscricoes/" + i.uuid()))
                .body(i);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}