package org.educavagas.api.services;

import org.educavagas.api.dto.TurmaDto;
import org.educavagas.api.model.Turma;
import org.educavagas.api.repository.TurmaRepository;
import org.educavagas.api.mapper.TurmaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TurmaService {
    private final TurmaRepository repository;
    private final TurmaMapper mapper;

    public TurmaService(TurmaRepository repository, TurmaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TurmaDto salvarTurma(TurmaDto dto) {
        Turma entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public TurmaDto alterarTurma(UUID uuid, TurmaDto dto) {
        if (!repository.existsById(uuid)) {
            throw new IllegalArgumentException("Turma não encontrada!");
        }
        Turma entity = mapper.toEntity(dto);
        entity.setUuid(uuid);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void deletarTurma(UUID uuid) {
        Turma entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada!"));
        repository.delete(entity);
    }

    public TurmaDto buscarTurmaPorId(UUID uuid) {
        Turma entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada!"));
        return mapper.toDto(entity);
    }

    public List<TurmaDto> listarTurmas() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}