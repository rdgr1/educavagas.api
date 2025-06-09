package org.educavagas.api.services;

import org.educavagas.api.dto.UsuarioEscolaDto;
import org.educavagas.api.model.UsuarioEscola;
import org.educavagas.api.repository.UsuarioEscolaRepository;
import org.educavagas.api.mapper.UsuarioEscolaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioEscolaService {
    private final UsuarioEscolaRepository repository;
    private final UsuarioEscolaMapper mapper;

    public UsuarioEscolaService(UsuarioEscolaRepository repository, UsuarioEscolaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UsuarioEscolaDto salvarUsuarioEscola(UsuarioEscolaDto dto) {
        UsuarioEscola entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public UsuarioEscolaDto alterarUsuarioEscola(UUID uuid, UsuarioEscolaDto dto) {
        if (!repository.existsById(uuid)) {
            throw new IllegalArgumentException("UsuárioEscola não encontrado!");
        }
        UsuarioEscola entity = mapper.toEntity(dto);
        entity.setUuid(uuid);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void deletarUsuarioEscola(UUID uuid) {
        UsuarioEscola entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("UsuárioEscola não encontrado!"));
        repository.delete(entity);
    }

    public UsuarioEscolaDto buscarUsuarioEscolaPorId(UUID uuid) {
        UsuarioEscola entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("UsuárioEscola não encontrado!"));
        return mapper.toDto(entity);
    }

    public List<UsuarioEscolaDto> listarUsuarioEscolas() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}