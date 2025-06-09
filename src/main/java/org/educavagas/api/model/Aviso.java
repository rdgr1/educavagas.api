package org.educavagas.api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.educavagas.api.util.OrigemAviso;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "aviso")
@Data
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false, length = 500)
    private String mensagem;

    @Column(nullable = false)
    private LocalDateTime dateEnvio;

    @Column(nullable = false)
    private OrigemAviso origemAviso;

    @ManyToOne
    @JoinColumn(name = "criado_por", nullable = false)
    private Usuario criadoPor;

    @ManyToOne
    @JoinColumn(name = "escola_uuid")
    private Escola escola;
}