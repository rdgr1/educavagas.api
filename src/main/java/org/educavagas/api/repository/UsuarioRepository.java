package org.educavagas.api.repository;

import org.educavagas.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

    Optional<Usuario> findByCpf(String cpf);
    Optional<Usuario> findByEmail(String email);
}
