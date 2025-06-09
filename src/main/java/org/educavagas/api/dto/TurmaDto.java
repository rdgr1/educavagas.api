package org.educavagas.api.dto;
import java.util.UUID;
import jakarta.validation.constraints.*;
public record TurmaDto(
        @NotNull(message = "UUID da turma é obrigatório")
        UUID uuid,

        @NotBlank(message = "Nome da turma é obrigatório")
        @Size(min = 1, max = 50, message = "Nome da turma deve ter entre {min} e {max} caracteres")
        String nome,

        @NotNull(message = "UUID da série associada é obrigatório")
        UUID serieUuid
) { }

