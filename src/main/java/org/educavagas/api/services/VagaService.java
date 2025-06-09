package org.educavagas.api.service;

import org.educavagas.api.dto.VagaDto;
import org.educavagas.api.mapper.VagaMapper;
import org.educavagas.api.model.Vaga;
import org.educavagas.api.repository.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VagaService {
    private final VagaRepository repo;
    private final VagaMapper     mapper;

    public VagaService(VagaRepository repo, VagaMapper mapper) {
        this.repo   = repo;
        this.mapper = mapper;
    }

    public List<VagaDto> listAll() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public VagaDto findById(UUID id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada"));
    }

    public VagaDto create(VagaDto dto) {
        Vaga v = mapper.toEntity(dto);
        v = repo.save(v);
        return mapper.toDto(v);
    }

    public VagaDto update(UUID id, VagaDto dto) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Vaga não encontrada");
        Vaga v = mapper.toEntity(dto);
        v.setUuid(id);
        v = repo.save(v);
        return mapper.toDto(v);
    }

    public void delete(UUID id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Vaga não encontrada");
        repo.deleteById(id);
    }
}