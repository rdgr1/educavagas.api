package org.educavagas.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuario_escole")
@Data
public class UsuarioEscola extends Usuario{
    private String matricula;
}
