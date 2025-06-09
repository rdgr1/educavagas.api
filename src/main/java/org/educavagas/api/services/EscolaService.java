package org.educavagas.api.services;


import org.educavagas.api.dto.EscolaDto;
import org.educavagas.api.model.Escola;
import org.educavagas.api.repository.EscolaRepository;
import org.educavagas.api.mapper.EscolaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EscolaService {
    private final EscolaRepository repository;
    private final EscolaMapper mapper;

    public EscolaService(EscolaRepository repository, EscolaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public EscolaDto salvarEscola(EscolaDto dto) {
        Escola entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public EscolaDto alterarEscola(UUID uuid, EscolaDto dto) {
        if (!repository.existsById(uuid)) {
            throw new IllegalArgumentException("Escola não encontrada!");
        }
        Escola entity = mapper.toEntity(dto);
        entity.setUuid(uuid);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void deletarEscola(UUID uuid) {
        Escola entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Escola não encontrada!"));
        repository.delete(entity);
    }

    public EscolaDto buscarEscolaPorId(UUID uuid) {
        Escola entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Escola não encontrada!"));
        return mapper.toDto(entity);
    }

    public List<EscolaDto> listarEscolas() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
