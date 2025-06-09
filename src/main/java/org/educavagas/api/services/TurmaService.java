package org.educavagas.api.service;

import org.educavagas.api.dto.TurmaDto;
import org.educavagas.api.mapper.TurmaMapper;
import org.educavagas.api.model.Turma;
import org.educavagas.api.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TurmaService {
    private final TurmaRepository repo;
    private final TurmaMapper     mapper;

    public TurmaService(TurmaRepository repo, TurmaMapper mapper) {
        this.repo   = repo;
        this.mapper = mapper;
    }

    public List<TurmaDto> listAll() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public TurmaDto findById(UUID id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
    }

    public TurmaDto create(TurmaDto dto) {
        Turma t = mapper.toEntity(dto);
        t = repo.save(t);
        return mapper.toDto(t);
    }

    public TurmaDto update(UUID id, TurmaDto dto) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Turma não encontrada");
        Turma t = mapper.toEntity(dto);
        t.setUuid(id);
        t = repo.save(t);
        return mapper.toDto(t);
    }

    public void delete(UUID id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Turma não encontrada");
        repo.deleteById(id);
    }
}