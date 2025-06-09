package org.educavagas.api.services;

import jakarta.validation.Valid;
import org.educavagas.api.dto.RegisterEscolaRequest;
import org.educavagas.api.dto.RegisterResponsavelRequest;
import org.educavagas.api.dto.ResponsavelDto;
import org.educavagas.api.dto.UsuarioEscolaDto;
import org.educavagas.api.mapper.ResponsavelMapper;
import org.educavagas.api.mapper.UsuarioEscolaMapper;
import org.educavagas.api.model.Escola;
import org.educavagas.api.repository.EscolaRepository;
import org.educavagas.api.repository.ResponsavelRepository;
import org.educavagas.api.repository.UsuarioEscolaRepository;
import org.educavagas.api.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UsuarioRepository        usuarioRepo;
    private final ResponsavelRepository    responsavelRepo;
    private final UsuarioEscolaRepository  usuarioEscolaRepo;
    private final EscolaRepository         escolaRepo;
    private final ResponsavelMapper        responsavelMapper;
    private final UsuarioEscolaMapper      usuarioEscolaMapper;
    private final PasswordEncoder          passwordEncoder;

    public RegistrationService(
            UsuarioRepository usuarioRepo,
            ResponsavelRepository responsavelRepo,
            UsuarioEscolaRepository usuarioEscolaRepo,
            EscolaRepository escolaRepo,
            ResponsavelMapper responsavelMapper,
            UsuarioEscolaMapper usuarioEscolaMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepo         = usuarioRepo;
        this.responsavelRepo     = responsavelRepo;
        this.usuarioEscolaRepo   = usuarioEscolaRepo;
        this.escolaRepo          = escolaRepo;
        this.responsavelMapper   = responsavelMapper;
        this.usuarioEscolaMapper = usuarioEscolaMapper;
        this.passwordEncoder     = passwordEncoder;
    }

    public ResponsavelDto registerResponsavel(@Valid RegisterResponsavelRequest dto) {
        if (usuarioRepo.existsByCpf(dto.cpf()))
            throw new IllegalArgumentException("CPF já cadastrado");
        if (usuarioRepo.existsByEmail(dto.email()))
            throw new IllegalArgumentException("E-mail já cadastrado");

        var entidade = responsavelMapper.toEntity(dto);
        entidade.setSenha(passwordEncoder.encode(dto.senha()));
        entidade.setAtivo(true);
        entidade = responsavelRepo.save(entidade);
        return responsavelMapper.toDto(entidade);
    }

    public UsuarioEscolaDto registerEscola(@Valid RegisterEscolaRequest dto) {
        if (usuarioRepo.existsByCpf(dto.cpf()))
            throw new IllegalArgumentException("CPF já cadastrado");
        if (usuarioRepo.existsByEmail(dto.email()))
            throw new IllegalArgumentException("E-mail já cadastrado");

        var entidade = usuarioEscolaMapper.toEntity(dto);
        entidade.setSenha(passwordEncoder.encode(dto.senha()));
        entidade.setAtivo(false);

        Escola escola = escolaRepo.findById(dto.escolaUuid())
                .orElseThrow(() -> new IllegalArgumentException("Escola não encontrada"));
        entidade.setEscola(escola);

        entidade = usuarioEscolaRepo.save(entidade);
        return usuarioEscolaMapper.toDto(entidade);
    }
}