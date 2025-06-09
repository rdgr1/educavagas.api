package org.educavagas.api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "inscricao_interesse")
@Data
public class InscricaoInteresse {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Instant criadoEm = Instant.now();
}
