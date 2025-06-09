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
    private Integer capacidade;
    private Integer matriculados;
    private Turno turno;
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    Instant criadaEM = Instant.now();
    @Column(nullable = false)
    Instant atualizadaEM = Instant.now();
}
