package org.educavagas.api.services;

import org.educavagas.api.dto.InscricaoInteresseDto;
import org.educavagas.api.mapper.InscricaoInteresseMapper;
import org.educavagas.api.model.InscricaoInteresse;
import org.educavagas.api.model.Responsavel;
import org.educavagas.api.model.Vaga;
import org.educavagas.api.repository.InscricaoInteresseRepository;
import org.educavagas.api.repository.ResponsavelRepository;
import org.educavagas.api.repository.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InscricaoInteresseService {
    private final InscricaoInteresseRepository repo;
    private final ResponsavelRepository       respRepo;
    private final VagaRepository              vagaRepo;
    private final InscricaoInteresseMapper    mapper;

    public InscricaoInteresseService(
            InscricaoInteresseRepository repo,
            ResponsavelRepository respRepo,
            VagaRepository vagaRepo,
            InscricaoInteresseMapper mapper
    ) {
        this.repo    = repo;
        this.respRepo = respRepo;
        this.vagaRepo = vagaRepo;
        this.mapper  = mapper;
    }

    public List<InscricaoInteresseDto> listAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public InscricaoInteresseDto findById(UUID id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Inscrição não encontrada"));
    }

    public InscricaoInteresseDto create(InscricaoInteresseDto dto) {
        // carrega as entidades relacionadas
        Responsavel r = respRepo.findById(dto.responsavelUuid())
                .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado"));
        Vaga v = vagaRepo.findById(dto.vagaUuid())
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada"));

        // mapeia DTO → Entity
        InscricaoInteresse ent = mapper.toEntity(dto);
        ent.setResponsavel(r);
        ent.setVaga(v);

        // persiste
        ent = repo.save(ent);

        // retorna Entity → DTO
        return mapper.toDto(ent);
    }

    public void delete(UUID id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Inscrição não encontrada");
        }
        repo.deleteById(id);
    }
}