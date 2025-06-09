package org.educavagas.api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "usuarios")
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(nullable = false)
    private String nomeCompleto;
    @Column(unique = true,nullable = false)
    private String cpf;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String senha;
    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    Instant criadoEm = Instant.now();
    @CreationTimestamp
    @Column(updatable = true,nullable = false)
    Instant atualizadoEm = Instant.now();
    private LocalDateTime ultimoLogin;
    private Set<Role> roles;
}
