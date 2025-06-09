package org.educavagas.api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.educavagas.api.util.Turno;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "vaga")
@Data
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private Integer capacidade;

    @Column(nullable = false)
    private Integer matriculados;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Turno turno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "turma_uuid", nullable = false)
    private Turma turma;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant criadaEM;

    @Column(nullable = false)
    private Instant atualizadaEM;
}