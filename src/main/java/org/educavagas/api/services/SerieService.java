package org.educavagas.api.service;

import org.educavagas.api.dto.SerieDto;
import org.educavagas.api.mapper.SerieMapper;
import org.educavagas.api.model.Serie;
import org.educavagas.api.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SerieService {
    private final SerieRepository repo;
    private final SerieMapper     mapper;

    public SerieService(SerieRepository repo, SerieMapper mapper) {
        this.repo   = repo;
        this.mapper = mapper;
    }

    public List<SerieDto> listAll() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public SerieDto findById(UUID id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Série não encontrada"));
    }

    public SerieDto create(SerieDto dto) {
        Serie s = mapper.toEntity(dto);
        s = repo.save(s);
        return mapper.toDto(s);
    }

    public SerieDto update(UUID id, SerieDto dto) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Série não encontrada");
        Serie s = mapper.toEntity(dto);
        s.setUuid(id);
        s = repo.save(s);
        return mapper.toDto(s);
    }

    public void delete(UUID id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Série não encontrada");
        repo.deleteById(id);
    }
}