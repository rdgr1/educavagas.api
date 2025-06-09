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
    private String mensagem;
    private LocalDateTime dateEnvio;
    private OrigemAviso origemAviso;
}
