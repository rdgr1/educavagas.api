package org.educavagas.api.services;

import org.educavagas.api.dto.VagaDto;
import org.educavagas.api.model.Vaga;
import org.educavagas.api.repository.VagaRepository;
import org.educavagas.api.mapper.VagaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VagaService {
    private final VagaRepository repository;
    private final VagaMapper mapper;

    public VagaService(VagaRepository repository, VagaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public VagaDto salvarVaga(VagaDto dto) {
        Vaga entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public VagaDto alterarVaga(UUID uuid, VagaDto dto) {
        if (!repository.existsById(uuid)) {
            throw new IllegalArgumentException("Vaga não encontrada!");
        }
        Vaga entity = mapper.toEntity(dto);
        entity.setUuid(uuid);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void deletarVaga(UUID uuid) {
        Vaga entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada!"));
        repository.delete(entity);
    }

    public VagaDto buscarVagaPorId(UUID uuid) {
        Vaga entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada!"));
        return mapper.toDto(entity);
    }

    public List<VagaDto> listarVagas() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}