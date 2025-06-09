package org.educavagas.api.services;

import org.educavagas.api.dto.SerieDto;
import org.educavagas.api.model.Serie;
import org.educavagas.api.repository.SerieRepository;
import org.educavagas.api.mapper.SerieMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SerieService {
    private final SerieRepository repository;
    private final SerieMapper mapper;

    public SerieService(SerieRepository repository, SerieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SerieDto salvarSerie(SerieDto dto) {
        Serie entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public SerieDto alterarSerie(UUID uuid, SerieDto dto) {
        if (!repository.existsById(uuid)) {
            throw new IllegalArgumentException("Série não encontrada!");
        }
        Serie entity = mapper.toEntity(dto);
        entity.setUuid(uuid);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void deletarSerie(UUID uuid) {
        Serie entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Série não encontrada!"));
        repository.delete(entity);
    }

    public SerieDto buscarSeriePorId(UUID uuid) {
        Serie entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Série não encontrada!"));
        return mapper.toDto(entity);
    }

    public List<SerieDto> listarSeries() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}