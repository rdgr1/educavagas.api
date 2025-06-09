package org.educavagas.api.services;

import org.educavagas.api.dto.AvisoDto;
import org.educavagas.api.model.Aviso;
import org.educavagas.api.repository.AvisoRepository;
import org.educavagas.api.mapper.AvisoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvisoService {
    private final AvisoRepository repository;
    private final AvisoMapper mapper;

    public AvisoService(AvisoRepository repository, AvisoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AvisoDto salvarAviso(AvisoDto dto) {
        Aviso entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public AvisoDto alterarAviso(UUID uuid, AvisoDto dto) {
        if (!repository.existsById(uuid)) {
            throw new IllegalArgumentException("Aviso não encontrado!");
        }
        Aviso entity = mapper.toEntity(dto);
        entity.setUuid(uuid);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void deletarAviso(UUID uuid) {
        Aviso entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Aviso não encontrado!"));
        repository.delete(entity);
    }

    public AvisoDto buscarAvisoPorId(UUID uuid) {
        Aviso entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Aviso não encontrado!"));
        return mapper.toDto(entity);
    }

    public List<AvisoDto> listarAvisos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}