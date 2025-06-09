package org.educavagas.api.security;

import org.educavagas.api.model.Usuario;
import org.educavagas.api.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import java.util.stream.*;

@Configuration
public class CustomUserDetailsService {

    private final UsuarioRepository usuarioRepo;

    public CustomUserDetailsService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario u = usuarioRepo.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
            var authorities = u.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNome()))
                    .collect(Collectors.toList());
            return User.builder()
                    .username(u.getEmail())
                    .password(u.getSenha())
                    .authorities(authorities)
                    .accountLocked(!u.isAtivo())
                    .build();
        };
    }
}