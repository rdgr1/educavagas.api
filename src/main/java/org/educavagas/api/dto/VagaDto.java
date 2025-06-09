package org.educavagas.api.dto;
import java.util.UUID;
import jakarta.validation.constraints.*;
public record VagaDto(
        @NotNull(message = "UUID da vaga é obrigatório")
        UUID uuid,

        @NotNull(message = "UUID da turma é obrigatório")
        UUID turmaUuid,

        @Min(value = 1, message = "Capacidade mínima é {value}")
        @Max(value = 500, message = "Capacidade máxima é {value}")
        int capacidade,

        @Min(value = 0, message = "Matriculados não pode ser negativo")
        int matriculados,

        @NotNull(message = "Data de criação é obrigatória")
        LocalDateTime criadaEm,

        @NotNull(message = "Data de atualização é obrigatória")
        LocalDateTime atualizadaEm
) { }

