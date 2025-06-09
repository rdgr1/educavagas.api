package org.educavagas.api.services;

import org.educavagas.api.dto.RegisterResponsavelRequest;
import org.educavagas.api.dto.ResponsavelDto;
import org.educavagas.api.model.Responsavel;
import org.educavagas.api.repository.ResponsavelRepository;
import org.educavagas.api.mapper.ResponsavelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResponsavelService {
    private final ResponsavelRepository repository;
    private final ResponsavelMapper mapper;

    public ResponsavelService(ResponsavelRepository repository, ResponsavelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ResponsavelDto salvarResponsavel(RegisterResponsavelRequest dto) {
        Responsavel entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public ResponsavelDto alterarResponsavel(UUID uuid, RegisterResponsavelRequest dto) {
        if (!repository.existsById(uuid)) {
            throw new IllegalArgumentException("Responsável não encontrado!");
        }
        Responsavel entity = mapper.toEntity(dto);
        entity.setUuid(uuid);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void deletarResponsavel(UUID uuid) {
        Responsavel entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado!"));
        repository.delete(entity);
    }

    public ResponsavelDto buscarResponsavelPorId(UUID uuid) {
        Responsavel entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado!"));
        return mapper.toDto(entity);
    }

    public List<ResponsavelDto> listarResponsaveis() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}