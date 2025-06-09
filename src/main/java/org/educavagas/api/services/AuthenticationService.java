package org.educavagas.api.services;

import org.educavagas.api.dto.UsuarioDto;
import org.educavagas.api.mapper.UsuarioMapper;
import org.educavagas.api.model.Usuario;
import org.educavagas.api.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UsuarioRepository usuarioRepo;
    private final UsuarioMapper     usuarioMapper;

    public AuthenticationService(
            UsuarioRepository usuarioRepo,
            UsuarioMapper usuarioMapper
    ) {
        this.usuarioRepo   = usuarioRepo;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioDto getCurrentUser(Authentication auth) {
        String email = auth.getName();
        Usuario u = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return usuarioMapper.toDto(u);
    }
}