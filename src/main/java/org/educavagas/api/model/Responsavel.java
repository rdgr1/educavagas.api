package org.educavagas.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "responsavel")
@Data
public class Responsavel extends Usuario{
    private String telefone;
    private String endereco;
}
