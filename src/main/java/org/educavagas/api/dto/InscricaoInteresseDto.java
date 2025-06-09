package org.educavagas.api.dto;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.validation.constraints.*;
public record InscricaoInteresseDto(
        @NotNull(message = "UUID da inscrição é obrigatório")
        UUID uuid,

        @NotNull(message = "UUID do responsável é obrigatório")
        UUID responsavelUuid,

        @NotNull(message = "UUID da vaga é obrigatório")
        UUID vagaUuid,

        @NotNull(message = "Data de criação da inscrição é obrigatória")
        LocalDateTime criadoEm
) { }
