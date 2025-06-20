package org.educavagas.api.repository;

import org.educavagas.api.model.UsuarioEscola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioEscolaRepository
        extends JpaRepository<UsuarioEscola, UUID>,
        UsuarioEscolaRepositoryCustom {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    Optional<UsuarioEscola> findByEmail(String email);
}