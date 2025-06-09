package org.educavagas.api.services;

import org.educavagas.api.dto.InscricaoInteresseDto;
import org.educavagas.api.model.InscricaoInteresse;
import org.educavagas.api.repository.InscricaoInteresseRepository;
import org.educavagas.api.mapper.InscricaoInteresseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InscricaoInteresseService {
    private final InscricaoInteresseRepository repository;
    private final InscricaoInteresseMapper mapper;

    public InscricaoInteresseService(InscricaoInteresseRepository repository, InscricaoInteresseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public InscricaoInteresseDto salvarInscricao(InscricaoInteresseDto dto) {
        InscricaoInteresse entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public InscricaoInteresseDto alterarInscricao(UUID uuid, InscricaoInteresseDto dto) {
        if (!repository.existsById(uuid)) {
            throw new IllegalArgumentException("Inscrição não encontrada!");
        }
        InscricaoInteresse entity = mapper.toEntity(dto);
        entity.setUuid(uuid);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void deletarInscricao(UUID uuid) {
        InscricaoInteresse entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Inscrição não encontrada!"));
        repository.delete(entity);
    }

    public InscricaoInteresseDto buscarInscricaoPorId(UUID uuid) {
        InscricaoInteresse entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Inscrição não encontrada!"));
        return mapper.toDto(entity);
    }

    public List<InscricaoInteresseDto> listarInscricoes() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}