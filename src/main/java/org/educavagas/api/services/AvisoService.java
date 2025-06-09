package org.educavagas.api.service;

import org.educavagas.api.dto.AvisoDto;
import org.educavagas.api.mapper.AvisoMapper;
import org.educavagas.api.model.Aviso;
import org.educavagas.api.model.Usuario;
import org.educavagas.api.model.Escola;
import org.educavagas.api.repository.AvisoRepository;
import org.educavagas.api.repository.UsuarioRepository;
import org.educavagas.api.repository.EscolaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AvisoService {
    private final AvisoRepository   repo;
    private final UsuarioRepository userRepo;
    private final EscolaRepository  escolaRepo;
    private final AvisoMapper       mapper;

    public AvisoService(
            AvisoRepository repo,
            UsuarioRepository userRepo,
            EscolaRepository escolaRepo,
            AvisoMapper mapper
    ) {
        this.repo      = repo;
        this.userRepo  = userRepo;
        this.escolaRepo = escolaRepo;
        this.mapper    = mapper;
    }

    public List<AvisoDto> listAll() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public AvisoDto findById(UUID id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Aviso não encontrado"));
    }

    public AvisoDto create(AvisoDto dto) {
        Usuario u = userRepo.findById(dto.criadoPorUuid())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        Escola e = null;
        if (dto.escolaUuid() != null) {
            e = escolaRepo.findById(dto.escolaUuid())
                    .orElseThrow(() -> new IllegalArgumentException("Escola não encontrada"));
        }

        Aviso a = mapper.toEntity(dto);
        a.setCriadoPor(u);
        a.setEscola(e);
        a = repo.save(a);
        return mapper.toDto(a);
    }

    public AvisoDto update(UUID id, AvisoDto dto) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Aviso não encontrado");
        Aviso a = mapper.toEntity(dto);
        a.setUuid(id);
        a = repo.save(a);
        return mapper.toDto(a);
    }

    public void delete(UUID id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Aviso não encontrado");
        repo.deleteById(id);
    }
}