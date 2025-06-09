package org.educavagas.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "usuario_escole")
@Data
public class UsuarioEscola extends Usuario {

    private String matricula;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "escola_uuid", nullable = false)
    private Escola escola;
}