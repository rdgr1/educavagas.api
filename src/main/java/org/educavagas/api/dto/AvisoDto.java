package org.educavagas.api.dto;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.validation.constraints.*;
public record AvisoDto(
        @NotNull(message = "UUID do aviso é obrigatório")
        UUID uuid,

        @NotBlank(message = "Mensagem do aviso é obrigatória")
        @Size(min = 3, max = 500, message = "Mensagem deve ter entre {min} e {max} caracteres")
        String mensagem,

        @NotNull(message = "Data de envio é obrigatória")
        LocalDateTime dateEnvio,

        @NotNull(message = "Origem do aviso é obrigatória")
        String origemAviso
) { }
