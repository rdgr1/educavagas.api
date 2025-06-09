package org.educavagas.api.services;

import org.educavagas.api.dto.RoleDto;
import org.educavagas.api.mapper.RoleMapper;
import org.educavagas.api.model.Role;
import org.educavagas.api.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    private final RoleRepository repo;
    private final RoleMapper     mapper;

    public RoleService(RoleRepository repo, RoleMapper mapper) {
        this.repo   = repo;
        this.mapper = mapper;
    }

    public List<RoleDto> listAll() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public RoleDto findById(UUID id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Role não encontrada"));
    }

    public RoleDto create(RoleDto dto) {
        Role r = mapper.toEntity(dto);
        r = repo.save(r);
        return mapper.toDto(r);
    }

    public RoleDto update(UUID id, RoleDto dto) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Role não encontrada");
        Role r = mapper.toEntity(dto);
        r.setUuid(id);
        r = repo.save(r);
        return mapper.toDto(r);
    }

    public void delete(UUID id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Role não encontrada");
        repo.deleteById(id);
    }
}