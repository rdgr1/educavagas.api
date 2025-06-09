package org.educavagas.api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false, length = 100)
    private String nomeCompleto;

    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant criadoEm;

    @CreationTimestamp
    @Column(nullable = false)
    private Instant atualizadoEm;

    private LocalDateTime ultimoLogin;

    @Column(nullable = false)
    private boolean ativo = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_role",
            joinColumns = @JoinColumn(name = "usuario_uuid"),
            inverseJoinColumns = @JoinColumn(name = "role_uuid"))
    private Set<Role> roles;
}