package org.educavagas.api.controller;

import org.educavagas.api.dto.RoleDto;
import org.educavagas.api.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public List<RoleDto> all() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDto> create(@RequestBody RoleDto dto) {
        RoleDto saved = service.create(dto);
        return ResponseEntity.created(URI.create("/roles/" + saved.uuid()))
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> update(
            @PathVariable UUID id,
            @RequestBody RoleDto dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}