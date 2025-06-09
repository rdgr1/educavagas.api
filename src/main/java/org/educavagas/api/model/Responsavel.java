package org.educavagas.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "responsavel")
@Data
public class Responsavel extends Usuario {
    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, length = 200)
    private String endereco;
}