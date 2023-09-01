package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.enums.MotivoCancelamento;

public record CancelamentoConsultaDto(
    
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo) {
}
