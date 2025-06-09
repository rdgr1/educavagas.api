package org.educavagas.api.services;

import org.educavagas.api.dto.UsuarioDto;
import org.educavagas.api.model.Usuario;
import org.educavagas.api.repository.UsuarioRepository;
import org.educavagas.api.mapper.UsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UsuarioDto salvarUsuario(UsuarioDto dto) {
        Usuario entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public UsuarioDto alterarUsuario(UUID uuid, UsuarioDto dto) {
        if (!repository.existsById(uuid)) {
            throw new IllegalArgumentException("Usuário não encontrado!");
        }
        Usuario entity = mapper.toEntity(dto);
        entity.setUuid(uuid);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void deletarUsuario(UUID uuid) {
        Usuario entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
        repository.delete(entity);
    }

    public UsuarioDto buscarUsuarioPorId(UUID uuid) {
        Usuario entity = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
        return mapper.toDto(entity);
    }

    public List<UsuarioDto> listarUsuarios() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}