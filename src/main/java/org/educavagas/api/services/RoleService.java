package org.educavagas.api.services;

import org.educavagas.api.dto.RoleDto;
import org.educavagas.api.model.Role;
import org.educavagas.api.repository.RoleRepository;
import org.educavagas.api.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;

    public RoleService(RoleRepository repository, RoleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RoleDto salvarRole(RoleDto dto) {
        Role entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public RoleDto alterarRole(UUID uuid, RoleDto dto) {
        if (!repository.existsById(uuid)) {
            throw new IllegalArgumentException("Role não encontrada!");
        }
        Role entity = mapper.toEntity(dto);
        entity.setUuid(uuid);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void deletarRole(UUID uuid) {
        Role entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Role não encontrada!"));
        repository.delete(entity);
    }

    public RoleDto buscarRolePorId(UUID uuid) {
        Role entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Role não encontrada!"));
        return mapper.toDto(entity);
    }

    public List<RoleDto> listarRoles() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}